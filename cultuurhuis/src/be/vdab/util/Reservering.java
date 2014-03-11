package be.vdab.util;

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
	

}
