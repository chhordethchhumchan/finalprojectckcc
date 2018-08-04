package restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name ="booking")
public class Booking {
	

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id")
	private int id;
	
	@Column(name ="customerName")
	private String customerName;
	
	@Column(name = "contactNo")
	private String contactNo;
	public Booking() {
		
	}
	public Booking(int id, String cusName, String contactNo) {
		this.id = id;
		this.customerName = cusName;
		this.contactNo = contactNo;
	}
	
	public String[] toStringData() {
		
		String booking[] = {this.id+"",this.customerName,this.contactNo };
		
		return booking;
	}
	
	
	public int getBookingID() {
		return this.id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}	
	
}