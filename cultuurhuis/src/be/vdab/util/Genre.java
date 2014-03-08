package be.vdab.util;

public class Genre implements Comparable {
	private String naam;
	private int nummer;
	
	public Genre(int nummer, String naam){
		this.nummer=nummer;
		this.naam=naam;
	}
	
	public String getNaam() {
		return naam;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	
	@Override
	public int compareTo(Object o){
		return (this.naam).compareTo(((Genre)o).naam);
	}


	
	
}
