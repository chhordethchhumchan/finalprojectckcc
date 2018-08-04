package restaurant;

import java.util.List;

public interface CRUD<T> {

	public void create(T object);
	public List<T> read();
	public void update(T object);
	public void delete(T object);
	
}
