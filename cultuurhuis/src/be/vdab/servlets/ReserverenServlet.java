package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.util.Voorstelling;

/**
 * Servlet implementation class ReserverenServlet
 */
@WebServlet("/reserveren")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final VoorstellingDAO voorstellingDAO=new VoorstellingDAO();
	private static final String VIEW="/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL="/reservatiemandje";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReserverenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int voorstellingsNummer=Integer.parseInt( request.getParameter("voorstelling"));
		Voorstelling voorstelling=voorstellingDAO.findByPK(voorstellingsNummer);
		request.setAttribute("voorstelling", voorstelling);
		@SuppressWarnings("unchecked")
		Map<Long,Integer>reservatiemandje= (Map<Long,Integer>) request.getSession().getAttribute("reservatiemandje");
		if(reservatiemandje!=null && reservatiemandje.containsKey(new Long(voorstellingsNummer))){
			request.setAttribute("plaatsen", reservatiemandje.get(new Long(voorstellingsNummer)));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int voorstellingsNummer=Integer.parseInt(request.getParameter("voorstelling"));
		Voorstelling voorstelling=voorstellingDAO.findByPK(voorstellingsNummer);
		request.setAttribute("voorstelling", voorstelling);
		
		try{
		 int aantalTeReserveren=Integer.parseInt( request.getParameter("aantalTeReserveren"));
		 if (aantalTeReserveren>=1 && aantalTeReserveren<=voorstelling.getAantalVrijePlaatsen()){
			 //reservering toevoegen aan winkelmandje
			 @SuppressWarnings("unchecked")
			 Map<Long,Integer> reservatiemandje= (Map<Long,Integer>)( request.getSession().getAttribute("reservatiemandje"));
			 if(reservatiemandje==null){
				 reservatiemandje=new HashMap<Long, Integer>();
			 }
			 reservatiemandje.put(new Long(voorstelling.getNummer()), aantalTeReserveren);// hoe via debuggen zien wat er in het mandje zit?
			 request.getSession().setAttribute("reservatiemandje", reservatiemandje);
			 response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
		 }else{
			 request.setAttribute("fout", "Tike een getal tussen 1 en "+voorstelling.getAantalVrijePlaatsen());
			 request.getRequestDispatcher(VIEW).forward(request, response);
		 }
		}catch(NumberFormatException nfe){//hoe gebruik maken van de restricties in de inputtag?
			request.setAttribute("fout", "Tike een getal tussen 1 en "+voorstelling.getAantalVrijePlaatsen());
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}

}
