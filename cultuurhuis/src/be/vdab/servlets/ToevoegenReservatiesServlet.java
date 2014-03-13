package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.ReservatieDAO;
import be.vdab.util.Klant;
import be.vdab.util.Reservering;
import be.vdab.util.Voorstelling;

/**
 * Servlet implementation class ToevoegenReservatiesServlet
 * extra servlet om de insert en update te verwerken achter de knop bevestigen
 */
@WebServlet("/reserveringen/toevoegen")
public class ToevoegenReservatiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final ReservatieDAO reservatieDAO=new ReservatieDAO();
	private static final String REDIRECT_URL="/reserveringen/overzicht";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToevoegenReservatiesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//voer de transactie uit per reservering in het reservatiemandje (reservering toevoegen in databank en aantal vrijePlaatsen verminderen)
		//ledig reservatiemandje
		
		//op de sessie staat een reservatiemandje Map<Long,Integer> en de klant
		Map<Long,Integer> reservatiemandje=(Map<Long,Integer>)request.getSession().getAttribute("reservatiemandje");
		List<Reservering> verwerkteReserveringen=new ArrayList<>();
		Map<Long,Integer> gelukteReserveringen=new HashMap<Long, Integer>();
		Map<Long,Integer> mislukteReserveringen=new HashMap<Long,Integer>();
		for(Entry<Long, Integer> entry:reservatiemandje.entrySet()){
			Voorstelling voorstelling=new Voorstelling(entry.getKey().intValue());
			Reservering reservatie=new Reservering(voorstelling, entry.getValue());
			@SuppressWarnings("unchecked")
			Klant klant=(Klant)request.getSession().getAttribute("klant");
			Reservering verwerkteReservering=reservatieDAO.bevestigReservatie(reservatie, klant.getNummer());//isGelukt is nu ingevuld --> beter elders(hier bv) ingevuld ipv in de DAO?
			verwerkteReserveringen.add(verwerkteReservering);
		}
		//wat op de sessie zetten zodat ik in de doGet van overzichtReserveringenServlet de tabelletjes met gelukte en mislukte reserveringen kan tonen. Rekening houdend met liefst zo weinig mogelijk op de sessie
		//kleinste wat mogelijk is volgens mij is een Map gelukte reserveringen met voorstellingsNummer en aantalplaatsen en een Map mislukte reserveringen
		for(Reservering reservering:verwerkteReserveringen){
			if(reservering.isGelukt()){
				gelukteReserveringen.put(new Long(reservering.getVoorstelling().getNummer()), reservering.getAantalPlaatsen());
			}else{
				mislukteReserveringen.put(new Long(reservering.getVoorstelling().getNummer()), reservering.getAantalPlaatsen());
			}
		}
		request.getSession().setAttribute("gelukteReserveringen", gelukteReserveringen);
		request.getSession().setAttribute("mislukteReserveringen", mislukteReserveringen);
		request.getSession().removeAttribute("reservatiemandje");
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
		
	}

}
