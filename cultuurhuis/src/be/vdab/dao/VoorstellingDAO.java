package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;


import java.util.LinkedList;
import java.util.List;

import java.util.StringTokenizer;


import be.vdab.util.Genre;
import be.vdab.util.Uitvoerder;
import be.vdab.util.Voorstelling;

public class VoorstellingDAO extends AbstractDAO {
	private static final String FIND_BY_GENRE="select VoorstellingsNr,Titel, Uitvoerders,Datum, Prijs, VrijePlaatsen,genres.GenreNr as genreNummer, genres.Naam as genreNaam from genres, voorstellingen where genres.GenreNr=voorstellingen.GenreNr and genres.naam= ? and Datum >=CURDATE() order by Datum";
	private static final String FIND_BY_PK="select VoorstellingsNr, Titel, Uitvoerders,Datum,Prijs, VrijePlaatsen,genres.GenreNr as genreNummer, genres.Naam as genreNaam from voorstellingen,genres where genres.GenreNr=voorstellingen.GenreNr and VoorstellingsNr=?";
	
	public ArrayList<Voorstelling> findByGenre(String genreNaam){
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(FIND_BY_GENRE);
				
				){
			statement.setString(1, genreNaam);
			try(
				ResultSet resultset=statement.executeQuery();){
				ArrayList<Voorstelling> voorstellingen=new ArrayList<>();
				while(resultset.next()){
					Voorstelling voorstelling=resultSetFindByGenreRijNaarVoorstelling(resultset);
					voorstellingen.add(voorstelling);
				}
				return voorstellingen;
			}
		}catch(SQLException ex){
			throw new DAOException("Kan voorstellingen niet vinden",ex);
		}
	}
	
	public Voorstelling findByPK(int voorstellingsNr){
		try(Connection connection=getConnection();
				PreparedStatement statement=connection.prepareStatement(FIND_BY_PK);){
			statement.setInt(1, voorstellingsNr);
			try(ResultSet resultset=statement.executeQuery();){
				resultset.next();
				Voorstelling voorstelling=resultSetFindByGenreRijNaarVoorstelling(resultset);
				return voorstelling;
			}
			
		}catch(SQLException ex){
			throw new DAOException("Kan voorstelling niet vinden met nummer "+voorstellingsNr,ex);
		}
	}
	
	
	private Voorstelling resultSetFindByGenreRijNaarVoorstelling(ResultSet resultset){
		
		try{
		Genre genre=new Genre(resultset.getInt("genreNummer"),resultset.getString("genreNaam"));
		List<Uitvoerder> uitvoerders=new ArrayList<Uitvoerder>();
		String uitvoerdersString=resultset.getString("Uitvoerders");
		StringTokenizer tokenizer=new StringTokenizer(uitvoerdersString, "&/,");
		while(tokenizer.hasMoreTokens()){
			Uitvoerder uitvoerder=new Uitvoerder(tokenizer.nextToken());
			uitvoerders.add(uitvoerder);
		}
		
		Voorstelling voorstelling=new Voorstelling(resultset.getInt("VoorstellingsNr"),resultset.getTimestamp("Datum"),resultset.getInt("VrijePlaatsen"),resultset.getString("Titel"),resultset.getDouble("Prijs"),genre,uitvoerders);
		return voorstelling;
		}catch(SQLException ex){
			throw new DAOException("Kan voorstellingen niet ophalen uit de databank", ex);
		}
	}
	

	

}
