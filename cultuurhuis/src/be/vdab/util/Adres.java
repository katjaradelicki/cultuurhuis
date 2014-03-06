package be.vdab.util;

public class Adres {
	private String straat;
	private String huisNummer;
	private int postcode;
	private String gemeente;
	
	public Adres(String straat,String huisnr, int postcode,String gemeente){
		this.straat=straat;
		huisNummer=huisnr;
		this.postcode=postcode;
		this.gemeente=gemeente;
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNummer() {
		return huisNummer;
	}

	public int getPostcode() {
		return postcode;
	}

	public String getGemeente() {
		return gemeente;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public void setHuisNummer(String huisNummer) {
		this.huisNummer = huisNummer;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public void setGemeente(String gemeente) {
		this.gemeente = gemeente;
	}
	
}
