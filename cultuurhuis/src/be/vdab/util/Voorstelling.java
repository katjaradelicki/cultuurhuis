package be.vdab.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Voorstelling {
	private int nummer;
	
	private Date datum;
	private int aantalVrijePlaatsen;
	private String titel;
	private double prijs;
	private Genre genre;
	private List<Uitvoerder> uitvoerders;
	
	public Voorstelling (int nummer, Date datum, int aantalVrijePlaatsen, String naam, double prijs, Genre genre,List<Uitvoerder>uitvoerders){
		this.nummer=nummer;
		this.datum=datum;
		this.aantalVrijePlaatsen=aantalVrijePlaatsen;
		this.titel=naam;
		this.prijs=prijs;
		this.genre=genre;
		this.uitvoerders=uitvoerders;
	}
	
	
	public List<Uitvoerder> getUitvoerders() {
		return uitvoerders;
	}


	public void setUitvoerders(List<Uitvoerder> uitvoerders) {
		this.uitvoerders = uitvoerders;
	}


	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public Genre getGenre() {
		return genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public Date getDatum() {
		return datum;
	}
	public int getAantalVrijePlaatsen() {
		return aantalVrijePlaatsen;
	}
	public String getTitel() {
		return titel;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public void setAantalVrijePlaatsen(int aantalVrijePlaatsen) {
		this.aantalVrijePlaatsen = aantalVrijePlaatsen;
	}
	public void setTitel(String naam) {
		this.titel = naam;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	
	
	
	
	
	
}
