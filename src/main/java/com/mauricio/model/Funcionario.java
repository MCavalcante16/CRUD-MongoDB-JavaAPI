package com.mauricio.model;

import java.time.LocalDate;

import org.bson.Document;
import org.bson.types.ObjectId;

public abstract class Funcionario {

	protected ObjectId id;
	protected String nome;
	protected String endereco;
	protected String sexo;
	protected LocalDate dataNascimento;
	protected Double salario;
	protected ObjectId idDepartamento;
	public abstract String toJson();
	
	public Funcionario() {
	}

	public Funcionario(ObjectId id, String nome, String endereco, String sexo, LocalDate dataNascimento,
			Double salario, ObjectId idDepartamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.salario = salario;
		this.idDepartamento = idDepartamento;
	}
	
	

	public ObjectId getId() {
		return id;
	}



	public void setId(ObjectId id) {
		this.id = id;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getEndereco() {
		return endereco;
	}



	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}



	public String getSexo() {
		return sexo;
	}



	public void setSexo(String sexo) {
		this.sexo = sexo;
	}



	public LocalDate getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public Double getSalario() {
		return salario;
	}
	
	public void setSalario(Double salario) {
		this.salario = salario;
	}

	
	
	public ObjectId getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(ObjectId idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public Document toDocument() {
		return Document.parse(this.toJson());
	}
}
