package restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tables")
public class Tables {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "numberSeats")
	private int numSeats;
	
	@Column(name = "available")
	private boolean available;
	public Tables () {}
	public Tables(int numSeats, String id, boolean available) {
		this.numSeats = numSeats;
		this.id = id;
		this.available = available;
	}
	
	public void setOccupied(boolean available) {
		this.available = available;
	}
	
	public boolean getOccupied() {
		return this.available;
	}
	
	public String getID() {
		return this.id;
	}
	public String[] toStringData() {
		String tableStatus = this.available? "Free" : "Busy";
		String tables[] = {this.id,this.numSeats+"",tableStatus+"" };
		return tables;
	}
	public String toString() {
		String tableStatus = this.available? "Free" : "Busy";
		return "'Table#" + this.id + "'   'Seats: " + this.numSeats + "'   '" + tableStatus + "'\n"; 
	}
	
}
