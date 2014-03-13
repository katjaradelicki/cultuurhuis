package be.vdab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import be.vdab.util.Reservering;

public class ReservatieDAO extends AbstractDAO{
	
	private static final String INSERT="insert into reservaties (KlantNr,VoorstellingsNr,Plaatsen) values (?,?,?)";
	//hoort hier eigenlijk niet , maar nodig in transactie. Hoe best oplossen?
	private static final String UPDATE_VRIJE_PLAATSEN_VOORSTELLINGEN="update voorstellingen set VrijePlaatsen=? where VoorstellingsNr=?";
	private static final String SELECT_VRIJE_PLAATSEN_VOORSTELLINGEN="select VrijePlaatsen from voorstellingen where VoorstellingsNr=?";
	
	public Reservering bevestigReservatie(Reservering reservatie,int klantnr){//niet alle reservaties tegelijk bevestigen want je moet weten welke reservatie mislukt is en welke niet, dus niet met batch
		//TRANSACTIE
		//0.VrijePlaatsen van tabel voorstellingen ophalen  //je moet sowieso aantal vrijePlaatsen eerst opvragen om ze dan zelf te verminderen en een update te doen met de verminderde plaatsen. Ik zou hier finfByPk van voostellingDAO kunnen gebruiken, maar dat gaat niet omdat ik het mee in 1 transactie wil steken
		//1.VrijePlaatsen verminderen in tabel voorstellingen (door een update te doen en te zien of die lukt of eerst VrijePlaantsen ophalen en zien dat dat er genoeg zijn en dan de update uit te voeren?maar dan krijg je geen exception omdat aantalVrijePlaatsen niet voldoende is en dat heb je voor de goede werking van de transactie wel nodig.)                                                    
		//2.record toevoegen in tabel reservaties
		Connection connection=null;
		
		//structuur met zoveel try-blokken ok?
		try{
			connection=getConnection();
			connection.setAutoCommit(false);
			try(PreparedStatement statement0=connection.prepareStatement(SELECT_VRIJE_PLAATSEN_VOORSTELLINGEN);){
				statement0.setInt(1, reservatie.getVoorstelling().getNummer());
				try(ResultSet resulset=statement0.executeQuery()){
					if(resulset.next()){
						int vrijePlaatsen=resulset.getInt("VrijePlaatsen");
						vrijePlaatsen-=reservatie.getAantalPlaatsen();
						try(PreparedStatement statement1=connection.prepareStatement(UPDATE_VRIJE_PLAATSEN_VOORSTELLINGEN)){
							statement1.setInt(1, vrijePlaatsen);
							statement1.setInt(2, reservatie.getVoorstelling().getNummer());
							statement1.executeUpdate();
							try(PreparedStatement statement2=connection.prepareStatement(INSERT)){
								statement2.setInt(1, klantnr);
								statement2.setInt(2, reservatie.getVoorstelling().getNummer());
								statement2.setInt(3, reservatie.getAantalPlaatsen());
								statement2.executeUpdate();
							}
						}
					}
				}
			}
			connection.commit();
			reservatie.setGelukt(true);
			return reservatie;
		}catch(SQLException ex){
			try{
			connection.rollback();
			}catch(SQLException sqlex){
				throw new DAOException("Kan connectie niet rollbacken", ex);
			}
			reservatie.setGelukt(false);
			return reservatie;
		}finally{
			if(connection!=null){
				try{connection.close();}catch(SQLException ex){throw new DAOException("problemen met sluiten van connectie",ex);}
			}
		}
	}
}
