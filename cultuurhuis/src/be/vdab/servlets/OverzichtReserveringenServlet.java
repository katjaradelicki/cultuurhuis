package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.VoorstellingDAO;
import be.vdab.util.Reservering;
import be.vdab.util.Voorstelling;

/**
 * Servlet die een overzicht geeft van de gelukte reservaties en de mislukte reservaties die een klant net heeft trachten uit te voeren/bevestigen
 */
@WebServlet("/reserveringen/overzicht")
public class OverzichtReserveringenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/overzichtReserveringen.jsp";
	private static final VoorstellingDAO voorstellingsDAO=new VoorstellingDAO();
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//voor elke reservering: gegevens van de voorstelling uit de databank halen en op de request zetten om in de JSP te kunnen lezen
		//(had ik  beter niet het volledig reservatie-object gewoon op de sessie gezet? door de gegevens nu nog eens te gaan ophalen heb ik wel de recenste gegevens)
		Map<Long,Integer>gelukteReserveringen=(Map<Long,Integer>) request.getSession().getAttribute("gelukteReserveringen");
		Map<Long,Integer>mislukteReserveringen=(Map<Long,Integer>) request.getSession().getAttribute("mislukteReserveringen");
		//reserveringen gesorteerd op datum in een lijst stoppen
		List<Reservering> gelukteReservatieLijst=new ArrayList<>();
		List<Reservering> mislukteReservatieLijst=new ArrayList<>();
		for(Entry<Long,Integer> entry:gelukteReserveringen.entrySet()){
			Voorstelling voorstelling=voorstellingsDAO.findByPK(entry.getKey().intValue());
			Reservering reservering=new Reservering(voorstelling, entry.getValue());
			gelukteReservatieLijst.add(reservering);
		}
		for(Entry<Long,Integer> entry:mislukteReserveringen.entrySet()){
			Voorstelling voorstelling=voorstellingsDAO.findByPK(entry.getKey().intValue());
			Reservering reservering=new Reservering(voorstelling, entry.getValue());
			mislukteReservatieLijst.add(reservering);
		}
		Collections.sort(gelukteReservatieLijst, Reservering.getDatumComparator()); 
		Collections.sort(mislukteReservatieLijst,Reservering.getDatumComparator());
		request.setAttribute("gelukteReservatieLijst", gelukteReservatieLijst);
		request.setAttribute("mislukteReservatieLijst", mislukteReservatieLijst);
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	

}
