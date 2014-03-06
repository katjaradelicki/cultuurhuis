package be.vdab.util;

import java.util.Calendar;

public class Voorstelling {
	private Calendar datum;
	private int aantalVrijePlaatsen;
	private String naam;
	private double prijs;
	private Genre genre;
	private Uitvoerder uitvoerder;
	
	public Genre getGenre() {
		return genre;
	}
	public Uitvoerder getUitvoerder() {
		return uitvoerder;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public void setUitvoerder(Uitvoerder uitvoerder) {
		this.uitvoerder = uitvoerder;
	}
	public Calendar getDatum() {
		return datum;
	}
	public int getAantalVrijePlaatsen() {
		return aantalVrijePlaatsen;
	}
	public String getNaam() {
		return naam;
	}
	public double getPrijs() {
		return prijs;
	}
	public void setDatum(Calendar datum) {
		this.datum = datum;
	}
	public void setAantalVrijePlaatsen(int aantalVrijePlaatsen) {
		this.aantalVrijePlaatsen = aantalVrijePlaatsen;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}
	
	
}
