package com.mauricio.dao;

import java.util.List;

import com.mauricio.model.Funcionario;



public interface FuncionarioDAO<T extends Funcionario> {

	public void insert(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public void delete(Object id);
	
	public T find(Object id);
	
	public List<T> findAll();
	
	public void close();
}
