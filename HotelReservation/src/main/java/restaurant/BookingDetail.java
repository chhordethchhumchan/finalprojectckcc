package restaurant;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name ="bookingdetail")
public class BookingDetail {
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="bookingID")
	private String reservedbTableID;
	

	@Column(name ="tableID")
	private String reservedbBookingID;
	
	
	public BookingDetail() {
		
	}
	public BookingDetail(String reservedbTableID, String reservedbBookingID) {
		this.reservedbTableID = reservedbTableID;
		this.reservedbBookingID = reservedbBookingID;
	}

	public String getReservedbTableID() {
		return reservedbTableID;
	}

	public void setReservedbTableID(String reservedbTableID) {
		this.reservedbTableID = reservedbTableID;
	}

	public String getReservedbBookingID() {
		return reservedbBookingID;
	}

	public void setReservedbBookingID(String reservedbBookingID) {
		this.reservedbBookingID = reservedbBookingID;
	}
}