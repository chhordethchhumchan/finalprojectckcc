package restaurant;

import javax.persistence.*;

@Entity
@Table(name = "food")
public class Food {
	
	@Id
	private String id;
	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private double price;
	public Food() {
		
	}
	public Food(String id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	public String getFoodID() {
		return this.id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String[] toStringData() {
		
		String food[] = {this.id,this.name,this.price+"" };
		return food;
	}
	
	public String toString() {
		return "'" + this.id + "'    '" + this.name + "'     '" + this.price + "'\n";
	}

}
