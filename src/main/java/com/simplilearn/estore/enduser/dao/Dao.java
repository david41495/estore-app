package com.simplilearn.estore.enduser.dao;
import java.util.List;


//Generic Interface for CRUD Operations
public interface Dao<T> {
	
	T get (long id);
	List<T> getAll();
	void save (T object);
	void update(T object);
	void delete(long id);
	
}
