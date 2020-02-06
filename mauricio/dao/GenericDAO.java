package com.mauricio.dao;

import java.util.List;

import com.mauricio.model.Bean;



public interface GenericDAO<T extends Bean> {

	public void insert(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public void delete(Object id);
	
	public T find(Object id);
	
	public List<T> findAll();
	
	public void close();
}
