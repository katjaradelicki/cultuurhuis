package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.KlantDAO;
import be.vdab.util.Klant;

/**
 * Servlet implementation class BevestigingReservatieServlet
 */
@WebServlet("/reservatiemandje/bevestiging")
public class BevestigingReservatieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/reservatiemandjeBevestiging.jsp";
	private static final KlantDAO klantDAO=new KlantDAO();
	private static final String REDIRECT_URL="/reservatiemandje/bevestiging";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BevestigingReservatieServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("pagina", "Bevestiging reservatie");
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// (parameters gebruikersnaam en paswoord) Beter: klantnr op de sessie zetten
		String gebruikersnaam=request.getParameter("gebruikersnaam");
		String paswoord=request.getParameter("paswoord");
		Klant klant=klantDAO.findByUsernameAndPassword(gebruikersnaam, paswoord);
		if(klant==null){
			request.setAttribute("fout", "Verkeerde gebruikersnaam of paswoord");
			request.getRequestDispatcher(VIEW).forward(request, response);
		}else{//klantgegevens nodig in reservatiemandjeBevestiging.jsp -> op de request zetten heeft geen zin hier. 
			  //Klant in een cookie of in de session bewaren. Enkel deze mogelijkheid? Enkel tekst in een cookie, geen objecten?
			//request.getSession().setAttribute("klantNr", klant.getNummer());
			request.getSession().setAttribute("klant", klant);
			response.sendRedirect(response.encodeURL(request.getContextPath()+ REDIRECT_URL));
		}
	}

}
