package com.mauricio.model;

import java.time.LocalDate;

import org.bson.Document;
import org.bson.types.ObjectId;

public class FuncionarioLimpeza extends Funcionario{
	
	private String cargo;
	private String jornadaTrabalho;
	
	public FuncionarioLimpeza() {
		
	}
	
	public FuncionarioLimpeza(ObjectId id, String nome, String endereco, String sexo, LocalDate dataNascimento,
			Double salario, String cargo, String jornadaTrabalho, ObjectId idDepartamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, idDepartamento);
		
		this.cargo = cargo;
		this.jornadaTrabalho = jornadaTrabalho;
	}
	
	
	
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public void setJornadaTrabalho(String jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	@Override
	public String toJson() {
		String json = "{\"cargo\" : \""+cargo+"\", "
					+ "\"jornadaTrabalho\" : \""+jornadaTrabalho+"\", "
					+ "\"nome\" : \""+nome+"\", "
					+ "\"endereco\" : \""+endereco+"\", "
					+ "\"sexo\" : \""+sexo+"\", "
					+ "\"dataNascimento\" : \""+dataNascimento+"\", "
					+ "\"idDepartamento\" : \""+idDepartamento+"\", "
					+ "\"salario\" : \""+salario+"\"}";
		return json;
	}
	

	public static FuncionarioLimpeza fromDocument(Document doc) {
		
		if(doc == null) return null;
		
		FuncionarioLimpeza FuncionarioLimpeza = new FuncionarioLimpeza();
		
		FuncionarioLimpeza.id = doc.getObjectId("_id");
		FuncionarioLimpeza.cargo = doc.getString("cargo");
		FuncionarioLimpeza.jornadaTrabalho = doc.getString("jornadaTrabalho");
		FuncionarioLimpeza.nome = doc.getString("nome");
		FuncionarioLimpeza.endereco = doc.getString("endereco");
		FuncionarioLimpeza.sexo  = doc.getString("sexo");
		FuncionarioLimpeza.dataNascimento = LocalDate.now();
		FuncionarioLimpeza.salario = Double.parseDouble(doc.getString("salario"));
		FuncionarioLimpeza.idDepartamento = new ObjectId(doc.getString("idDepartamento")); 
		
		return FuncionarioLimpeza;
	}
	
	
	
}
