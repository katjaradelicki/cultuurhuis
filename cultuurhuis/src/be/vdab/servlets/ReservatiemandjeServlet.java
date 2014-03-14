package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;

import java.util.LinkedHashMap;

import java.util.List;

import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.util.ReservatieMandje;
import be.vdab.util.Reservering;
import be.vdab.util.Voorstelling;

/**
 * Servlet waar de gegevens van de reservaties die een klant wil maken uit de databank worden gehaald.
 * In doPost wordt het verwijderen van een reservatie in het reservatiemandje verwerkt
 */
@WebServlet("/reservatiemandje")
public class ReservatiemandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final VoorstellingDAO voorstellingDAO=new VoorstellingDAO();
	private static final String VIEW="/WEB-INF/JSP/reservatiemandje.jsp";
	private static final String REDIRECT_URL="/reservatiemandje";
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//reservatiemandje tonen. De voorstellingennummers omzetten naar voorstellingsobjecten en dat op de request zetten. 
		//(kan daar meer op dan op een session? want op de session enkel nummers bijhouden. In de jsp ben je met alleen nummers niets en wil je de volledige objecten)
		request.getSession().setAttribute("pagina", "Reservatiemandje");//sessie-attribuut om te weten naar welke pagina je gaat en zo wordt bepaald welke links er bovenaan moeten verschijnen hier bv 'voorstellingen' en 'bevestig reservatie'
		List<Reservering> gewensteReserveringen=new ArrayList<>();
		@SuppressWarnings("unchecked")
		LinkedHashMap<Long, Integer> reservatiemandje=(LinkedHashMap<Long,Integer>) request.getSession().getAttribute("reservatiemandje");
		
		for(Entry<Long,Integer> entry:reservatiemandje.entrySet()){
			
			Voorstelling voorstelling=voorstellingDAO.findByPK( entry.getKey().intValue());//waarom moet de key eigenlijk een Long zijn?
			Reservering reservering=new Reservering(voorstelling, entry.getValue());
			gewensteReserveringen.add(reservering);
			
		}
		ReservatieMandje reservatieMandjeVolledig=new ReservatieMandje(gewensteReserveringen);
		request.setAttribute("reservatieMandjeVolledig", reservatieMandjeVolledig);
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// aangevinkte reservaties verwijderen uit reservatiemandje
		//en dan terug reservatiemandje laten zien
		//request parameters ophalen van aangvinkte reserveringen 
		String[] teVerwijderenVoorstellingen= request.getParameterValues("verwijderenCheckbox");
		LinkedHashMap<Long, Integer> reservatiemandje=(LinkedHashMap<Long,Integer>) request.getSession().getAttribute("reservatiemandje");
		for(String voorstellingsNr:teVerwijderenVoorstellingen){
			reservatiemandje.remove(Long.parseLong(voorstellingsNr));
		}
		request.getSession().setAttribute("reservatiemandje", reservatiemandje);
		response.sendRedirect(response.encodeURL(request.getContextPath()+REDIRECT_URL));
	}

}
