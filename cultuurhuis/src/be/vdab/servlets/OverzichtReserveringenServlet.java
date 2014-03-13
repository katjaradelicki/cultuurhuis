package be.vdab.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OverzichtReserveringenServlet
 */
@WebServlet("/reserveringen/overzicht")
public class OverzichtReserveringenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/overzichtReserveringen.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OverzichtReserveringenServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//voor elke reservering en gegevens van de voorstelling uit de databank halen en op de request zetten om in de JSP te kunnen lezen(had ik het beter niet gewoon op de sessie gezet? nu heb ik wel de recenste gegevens)
		Map<Long,Integer>gelukteReserveringen=(Map<Long,Integer>) request.getSession().getAttribute("gelukteReserveringen");
		Map<Long,Integer>mislukteReserveringen=(Map<Long,Integer>) request.getSession().getAttribute("gelukteReserveringen");
		//reserveringen gesorteerd op datum in een lijst stoppen
		 
		request.getRequestDispatcher(VIEW).forward(request, response);
		
	}

	

}
