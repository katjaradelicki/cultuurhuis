package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import be.vdab.util.Klant;

public class KlantDAO extends AbstractDAO{
	private static final String FIND_USERNAME_AND_PASSWORD="select KlantNr,Voornaam,Familienaam,Straat,HuisNr,Postcode,Gemeente,GebruikersNaam,Paswoord from klanten where GebruikersNaam=? and Paswoord=?";
	private static final String INSERT="insert into klanten(Voornaam,Familienaam,Straat,HuisNr,Postcode,Gemeente,GebruikersNaam,Paswoord) values(?,?,?,?,?,?,?,?)";
	private static final String FIND_BY_USERNAME="select KlantNr from klanten where GebruikersNaam=?";

	public Klant findByUsernameAndPassword(String username,String password){
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(FIND_USERNAME_AND_PASSWORD);){
			statement.setString(1, username);
			statement.setString(2, password);
			try(ResultSet resultset=statement.executeQuery();){
				Klant klant=null;
				if(resultset.next()){
					klant=resultSetRijNaarKlant(resultset);
					}
				return klant;
			}
			
		}catch(SQLException ex){
			throw new DAOException("Kan user niet vinden", ex);
		}
	}
	
	private Klant resultSetRijNaarKlant(ResultSet resultset) throws SQLException{
		Klant klant=new Klant(resultset.getInt("KlantNr"),resultset.getString("Voornaam"),resultset.getString("Familienaam"),resultset.getString("Straat"),resultset.getString("HuisNr"),resultset.getInt("Postcode"),resultset.getString("Gemeente"),resultset.getString("GebruikersNaam"),resultset.getString("Paswoord"));
	    return klant;
	}
	/*
	 * @return klantnummer van de geïnserte klant
	 */
	public int insert(String voornaam,String familienaam,String straat, String huisNr,int postcode,String gemeente,String gebruikersNaam,String paswoord){
		
		//als gebruikersnaam er al inzit krijg je Exception bij het inserten (dus niet eerst kijken of gebruikersnaam er al inzit)
		//-->toch wel: je moet een foutmelding geven als gebruiker al bestaat (en niet bij elke SQLException)
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);){
			statement.setString(1, voornaam);
			statement.setString(2, familienaam);
			statement.setString(3, straat);
			statement.setString(4, huisNr);
			statement.setInt(5, postcode);
			statement.setString(6, gemeente);
			statement.setString(7, gebruikersNaam);
			statement.setString(8, paswoord);
			statement.executeUpdate();
			try(ResultSet resultset=statement.getGeneratedKeys()){
				int klantnummer;
				resultset.next();
				klantnummer=resultset.getInt(1);
				return klantnummer;
			}
			
		}catch(SQLException ex){
			throw new DAOException("Kan klant niet toevoegen", ex);
		}
		
	}
	
	public boolean isBestaand(String gebruikersnaam){
		boolean bestaand=false;
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(FIND_BY_USERNAME);){
			statement.setString(1, gebruikersnaam);
			try(ResultSet resultset=statement.executeQuery()){
				if(resultset.next()){
					bestaand=true;
				}
			}
			return bestaand;
		}catch(SQLException ex){
			throw new DAOException("Kan klant niet opzoeken", ex);
		}
	}
	
}
