package restaurant;

import java.util.ArrayList;
import java.util.List;

public class Reservation implements CRUD<Booking> {
	
	private List<Booking> reservationList;
	
	public Reservation() {
		reservationList = new ArrayList<Booking>();
	}
	
	public void create(Booking b) {
		reservationList.add(b);
	}
	
	public ArrayList<Booking> read(){
		return (ArrayList<Booking>)this.reservationList;
	}
	
	public void update(Booking b) {
		for(Booking booking: this.reservationList) {
			if(booking.getBookingID() == b.getBookingID()) {
				int ind = this.reservationList.indexOf(booking);
				this.reservationList.set(ind,b);
				break;
			}
		}
	}
	
	public void delete(Booking b) {
		reservationList.remove(b);
	}
	
	public String toString() {
		String reservationList = "";
		for(Booking booking : this.read()) {
			reservationList += booking.toString() + "\n";
		}
		return reservationList;
	}

}
