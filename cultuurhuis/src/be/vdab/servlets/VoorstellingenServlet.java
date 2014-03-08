package be.vdab.servlets;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;
import be.vdab.util.Genre;

/**
 * Servlet implementation class VoorstellingenServlet
 */
@WebServlet("/voorstellingen")
public class VoorstellingenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GenreDAO genreDAO=new GenreDAO();
	private static final Set<Genre> genres=genreDAO.findAll();//of Strings insteken? minder op de context bewaren
    private static final String VIEW="/WEB-INF/JSP/voorstellingen.jsp"  ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoorstellingenServlet() {
        //findAll 1 maal doen bij initialisatie ipv elke keer bij een doGet (OK want verandert niet vaak)
    	//in sessionContext steken of als private static final attribuut hier?
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("genres", genres);
		RequestDispatcher dispatcher=request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

}
