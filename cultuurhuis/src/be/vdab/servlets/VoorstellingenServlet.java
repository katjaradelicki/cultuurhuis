package be.vdab.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.GenreDAO;
import be.vdab.dao.VoorstellingDAO;
import be.vdab.util.Genre;
import be.vdab.util.Voorstelling;

/**
 * Servlet implementation class VoorstellingenServlet
 */
@WebServlet("/voorstellingen")
public class VoorstellingenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final GenreDAO genreDAO=new GenreDAO();
	private static final VoorstellingDAO voorstellingDAO=new VoorstellingDAO();//niet in doGet gestopt want dan wordt er bij elke thread/request een instantie aangemaakt en zo gebeurt het maar 1 keer. De requests delen deze DAO. 
	private static final Set<Genre> genres=genreDAO.findAll();//wordt één keer opgehaald uit databank bij initialisatie van deze servlet. Initialisatie van de servlet gebeurt 1 keer bij opstarten van de applicatie of bij eerste request.
    private static final String VIEW="/WEB-INF/JSP/voorstellingen.jsp"  ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    
    public VoorstellingenServlet() {
        
    	
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("pagina", "Voorstellingen");
		String gekozenGenre=request.getParameter("genre");
		request.setAttribute("genres", genres);
		if(gekozenGenre!=null && !gekozenGenre.isEmpty()){
			request.getSession().setAttribute("genre", gekozenGenre);
			LinkedList<Voorstelling> voorstellingen=voorstellingDAO.findByGenre(gekozenGenre);//LinkedList gebruiken omdat de sortering behouden moet blijven
			request.setAttribute("voorstellingen", voorstellingen);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(VIEW);
		dispatcher.forward(request, response);
	}

}
