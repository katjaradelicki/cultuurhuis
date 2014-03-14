package be.vdab.util;

import java.util.Comparator;

public class Reservering {
	
	private int reserveringsNr;
	private Voorstelling voorstelling;
	private int aantalPlaatsen;
	private boolean isGelukt;
	
	public Reservering(Voorstelling voorstelling, int aantalPlaatsen){
		this.voorstelling=voorstelling;
		this.aantalPlaatsen=aantalPlaatsen;
	}
	public int getReserveringsNr() {
		return reserveringsNr;
	}
	public Voorstelling getVoorstelling() {
		return voorstelling;
	}
	public int getAantalPlaatsen() {
		return aantalPlaatsen;
	}
	public boolean isGelukt() {
		return isGelukt;
	}
	public void setReserveringsNr(int reserveringsNr) {
		this.reserveringsNr = reserveringsNr;
	}
	public void setVoorstelling(Voorstelling voorstelling) {
		this.voorstelling = voorstelling;
	}
	public void setAantalPlaatsen(int aantalPlaatsen) {
		this.aantalPlaatsen = aantalPlaatsen;
	}
	public void setGelukt(boolean isGelukt) {
		this.isGelukt = isGelukt;
	}
	
	public static Comparator<Reservering> getDatumComparator(){
		return new Comparator<Reservering>() {
			
			@Override
			public int compare (Reservering r1, Reservering r2){
				if(r1==null || r2==null ){
					throw new NullPointerException();
				}else{
					if(r1.getVoorstelling().getNummer()== r2.getVoorstelling().getNummer()){
						return 0;
					}else {
						if(r1.getVoorstelling().getDatum().compareTo(r2.getVoorstelling().getDatum())<=0){
							return -1;
						}
						else{ return 1;}
					
						
					}
				}
			}
			
		};
	}
	
	
}
