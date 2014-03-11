package be.vdab.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
 * Servlet implementation class ReservatiemandjeServlet
 */
@WebServlet("/reservatiemandje")
public class ReservatiemandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final VoorstellingDAO voorstellingDAO=new VoorstellingDAO();
	private static final String VIEW="/WEB-INF/JSP/reservatiemandje.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservatiemandjeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//reservatiemandje tonen. De voorstellingennummers omzetten naar voorstellingsobjecten en dat op de request zetten. 
		//(kan daar meer op dan op een session? want op de session enkel nummers bijhouden. In de jsp ben je met alleen nummers niets)
		List<Reservering> gewensteReserveringen=new LinkedList<>();
		@SuppressWarnings("unchecked")
		Map<Long, Integer> reservatiemandje=(Map<Long,Integer>) request.getSession().getAttribute("reservatiemandje");
		
		for(Entry<Long,Integer> entry:reservatiemandje.entrySet()){
			
			Voorstelling voorstelling=voorstellingDAO.findByPK( entry.getKey().intValue());//waarom moet de key eigenlijk een Long zijn?
			Reservering reservering=new Reservering(voorstelling, entry.getValue());
			gewensteReserveringen.add(reservering);
			
		}
		ReservatieMandje reservatieMandjeVolledig=new ReservatieMandje(gewensteReserveringen);
		request.setAttribute("reservatieMandjeVolledig", reservatieMandjeVolledig);
		
		request.getRequestDispatcher(VIEW).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// aangevinkte reservaties verwijderen uit reservatiemandje
		//en dan terug reservatiemandje laten zien
		//request parameters ophalen van aangvinkte reserveringen (worden die in url erbij gezet->herlezen)
	}

}
