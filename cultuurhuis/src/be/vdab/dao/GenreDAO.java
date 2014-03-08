package be.vdab.dao;

import be.vdab.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class GenreDAO extends AbstractDAO {
	private static final String FIND_ALL="select GenreNr, Naam from genres order by Naam";
	public Set<Genre> findAll(){
		
		try(Connection connection=getConnection();
				Statement statement=connection.createStatement();
				ResultSet resultset=statement.executeQuery(FIND_ALL)){
			Set<Genre> genres=new TreeSet<>();
			while(resultset.next()){
				Genre genre=new Genre(resultset.getInt("GenreNr"),resultset.getString("Naam"));
				genres.add(genre);
			}
			return genres;
		}catch(SQLException ex){
			throw new DAOException("Kan genres niet vinden",ex);
		}
		
	}
}
