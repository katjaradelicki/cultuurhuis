package be.vdab.servlets;

import java.io.IOException;

import java.util.LinkedHashMap;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.util.Voorstelling;

/**
 * Servlet waar je een hoeveelheid plaatsen kan reserveren voor 1 bepaalde voorstelling. 
 * Als de klant deze voorstelling al in zijn mandje heeft, haal je de hoeveelheid plaatsen op die hij reeds reserveerde in zijn mandje
 */
@WebServlet("/reserveren")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final VoorstellingDAO voorstellingDAO=new VoorstellingDAO();
	private static final String VIEW="/WEB-INF/JSP/reserveren.jsp";
	private static final String REDIRECT_URL="/reservatiemandje";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("pagina", "Reserveren");//of als attribuut van de custom tag? ipv sessie-attribuut
		int voorstellingsNummer=Integer.parseInt( request.getParameter("voorstelling"));
		Voorstelling voorstelling=voorstellingDAO.findByPK(voorstellingsNummer);
		request.setAttribute("voorstelling", voorstelling);
		@SuppressWarnings("unchecked")
		LinkedHashMap<Long,Integer>reservatiemandje= (LinkedHashMap<Long,Integer>) request.getSession().getAttribute("reservatiemandje");
		if(reservatiemandje!=null && reservatiemandje.containsKey(new Long(voorstellingsNummer))){
			request.setAttribute("plaatsen", reservatiemandje.get(new Long(voorstellingsNummer)));
		}
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int voorstellingsNummer=Integer.parseInt(request.getParameter("voorstelling"));
		Voorstelling voorstelling=voorstellingDAO.findByPK(voorstellingsNummer);
		request.setAttribute("voorstelling", voorstelling);//request attribuut nodig als je een fout hebt
		try{
		
		int aantalTeReserveren=Integer.parseInt( request.getParameter("aantalTeReserveren"));
		if (aantalTeReserveren>=1 && aantalTeReserveren<=voorstelling.getAantalVrijePlaatsen()){
				 //reservering toevoegen aan winkelmandje
				 @SuppressWarnings("unchecked")
				 LinkedHashMap<Long,Integer> reservatiemandje= (LinkedHashMap<Long,Integer>)( request.getSession().getAttribute("reservatiemandje"));
				 if(reservatiemandje==null){
					 reservatiemandje=new LinkedHashMap<Long, Integer>(); //LinkedHashMap nodig ipv gewone hashmap want de insertion-order moet behouden blijven
				 }
			 reservatiemandje.put(new Long(voorstelling.getNummer()), aantalTeReserveren); 
			 request.getSession().setAttribute("reservatiemandje", reservatiemandje);
			 response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
		 }else{
			 request.setAttribute("fout", "Tik een getal tussen 1 en "+voorstelling.getAantalVrijePlaatsen());
			 request.getRequestDispatcher(VIEW).forward(request, response);
		 }
		}catch(NumberFormatException nfe){
			request.setAttribute("fout", "Tik een getal tussen 1 en "+voorstelling.getAantalVrijePlaatsen());
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
		
	}

}
