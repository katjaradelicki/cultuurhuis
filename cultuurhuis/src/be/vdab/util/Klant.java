package be.vdab.util;

import java.util.List;

public class Klant {
	
	private int nummer;
	private String voornaam;
	private String familienaam;
	private String gebruikersnaam;
	private String paswoord;
	private Adres adres;
	private List<Reservering> reserveringen;
	
	
	public Klant(int nummer,String voornaam,String familienaam,String straat,String huisNr,int postcode,String gemeente,String gebruikersnaam,String paswoord){
		this.nummer=nummer;
		this.voornaam=voornaam;
		this.familienaam=familienaam;
		this.gebruikersnaam=gebruikersnaam;
		this.paswoord=paswoord;
		this.adres=new Adres(straat,huisNr,postcode,gemeente);
	}
	
	@Override
	public String toString(){
		StringBuffer stringBuffer=new StringBuffer();
		stringBuffer=stringBuffer.append(voornaam).append(" ").append(familienaam).append(" ").append(adres.getStraat()).append(" ").append(adres.getHuisNummer()).append(" ").append(adres.getPostcode());
		stringBuffer=stringBuffer.append(" ").append(adres.getGemeente());
		return stringBuffer.toString();
	}
	
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
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
