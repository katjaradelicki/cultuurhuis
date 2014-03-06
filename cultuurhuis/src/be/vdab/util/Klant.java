package be.vdab.util;

import java.util.List;

public class Klant {
	
	private String voornaam;
	private String familienaam;
	private String gebruikersnaam;
	private String paswoord;
	private Adres adres;
	private List<Reservering> reserveringen;
	public String getVoornaam() {
		return voornaam;
	}
	public String getFamilienaam() {
		return familienaam;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public String getPaswoord() {
		return paswoord;
	}
	public Adres getAdres() {
		return adres;
	}
	public List<Reservering> getReserveringen() {
		return reserveringen;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public void setPaswoord(String paswoord) {
		this.paswoord = paswoord;
	}
	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	public void setReserveringen(List<Reservering> reserveringen) {
		this.reserveringen = reserveringen;
	}
	
	

}
