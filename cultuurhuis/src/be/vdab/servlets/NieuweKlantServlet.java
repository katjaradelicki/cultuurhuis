package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.KlantDAO;
import be.vdab.util.Klant;

/**
 * Servlet implementation class NieuweKlantServlet
 */
@WebServlet("/nieuweKlant")
public class NieuweKlantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW="/WEB-INF/JSP/nieuweKlant.jsp";
	private static final KlantDAO klandtDAO=new KlantDAO();
	private static final String REDIRECT_URL="/reservatiemandje/bevestiging";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NieuweKlantServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("pagina", "Nieuwe klant");
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <String> fouten=new LinkedList<>();
		//kan dit korter geschreven worden? Of elke parameter afgaan, zoals hier?
		String voornaam=request.getParameter("voornaam");
		if(voornaam.length()==0){
			fouten.add("voornaam niet ingevuld");
		}
		String familienaam=request.getParameter("familienaam");
		if(familienaam.length()==0){
			fouten.add("famielienaam niet ingevuld");
		}
		String straat=request.getParameter("straat");
		if(straat.length()==0){
			fouten.add("straat niet ingevuld");
		}
		String huisnr=request.getParameter("huisnr");
		if(huisnr.length()==0){
			fouten.add("huisnr niet ingevuld");
		}
		String postcode=request.getParameter("postcode");
		if(postcode.length()==0){
			fouten.add("postcode niet ingevuld");
		}
		String gemeente=request.getParameter("gemeente");
		if(gemeente.length()==0){
			fouten.add("gemeente niet ingevuld");
		}
		String gebruikersnaam=request.getParameter("gebruikersnaam");
		if(gebruikersnaam.length()==0){
			fouten.add("gebruikersnaam niet ingevuld");
		}
		String paswoord=request.getParameter("paswoord");
		if(paswoord.length()==0){
			fouten.add("paswoord niet ingevuld");
		}
		String herhaaldPaswoord=request.getParameter("herhaaldPaswoord");
		if(herhaaldPaswoord.length()==0){
			fouten.add("herhaal paswoord niet ingevuld");
		}
		if(!paswoord.equals(herhaaldPaswoord)){
			fouten.add("paswoord en herhaal paswoord zijn verschillend");
		}
		if(klandtDAO.isBestaand(gebruikersnaam)){
			fouten.add("Gebruikersnaam bestaat al");
		}
		if(!fouten.isEmpty()){
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}else{
			//geen fouten, dus nieuwe klant toevoegen en op sessie zetten
			int klantNr=klandtDAO.insert(voornaam, familienaam, straat, huisnr,Integer.parseInt( postcode), gemeente, gebruikersnaam, paswoord);
			Klant nieuweKlant=new Klant(klantNr,voornaam,familienaam,straat,huisnr,Integer.parseInt(postcode),gemeente,gebruikersnaam,paswoord);
			request.getSession().setAttribute("klant", nieuweKlant);
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath()+REDIRECT_URL));
		}
	
	}
	
	

}
