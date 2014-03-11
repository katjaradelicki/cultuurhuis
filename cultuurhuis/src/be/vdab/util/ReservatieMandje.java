package be.vdab.util;

import java.util.List;

public class ReservatieMandje {
	private List<Reservering> reserveringen;
	
	public ReservatieMandje(List<Reservering> reserveringen){
		this.reserveringen=reserveringen;
	}
	
	public double berekenTotaal(){
		double som=0;
		for(int i=0;i<reserveringen.size();i++){
			som+=(reserveringen.get(i).getVoorstelling().getPrijs())*(reserveringen.get(i).getAantalPlaatsen());
		}
		return som;
	}

	public List<Reservering> getReserveringen() {
		return reserveringen;
	}

	
	
	
}
