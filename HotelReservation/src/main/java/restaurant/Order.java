package restaurant;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Entity
@Table(name = "order")
public class Order implements CRUD<Food> {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "table_id")
	private Tables table;
	
	
	
	private List<Food> foodList;
	
	public Order(int id, Tables table) {
		this.id = id;
		this.table = table;
		foodList = new ArrayList<Food>();
	}
	
	public void create(Food f) {
		foodList.add(f);
	}
	
	public ArrayList<Food> read(){
		SessionFactory factoryStudent =  new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Food.class)
				.buildSessionFactory();
		Session session = factoryStudent.getCurrentSession();
		try {
			
			session.beginTransaction();
			Query queryResult = session.createQuery("from Food");
			
			List<Food> foodList  = queryResult.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (ArrayList<Food>)this.foodList;
	}
	
	public void update(Food f) {
		for(Food food: this.foodList) {
			if(food.getFoodID() == f.getFoodID()) {
				int ind = this.foodList.indexOf(food);
				this.foodList.set(ind,f);
				break;
			}
		}
	}
	
	public void delete(Food f) {
		foodList.remove(f);
	}
	public String[] toStringData() {
		String data[] = {
				
		}; 
		return data ;
	}
	public String toString() {
		String orderList = "";
		orderList += "Order #" + this.id + " | " + " Table #" + this.table.getID() + "\n";
		orderList += "----------------------------------------------------" + "\n";
		orderList += "[Code        Name                           Price]" + "\n";
		for(Food food : this.read()) {
			orderList += food.toString() + "\n";
		}
		return orderList;
	}
	
	
}

