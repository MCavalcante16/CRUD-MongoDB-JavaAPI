package com.mauricio.model;

import java.time.LocalDate;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Secretario extends Funcionario{

	private String grauEscolaridade;

	
	
	public Secretario() {

	}

	public Secretario(ObjectId id, String nome, String endereco, String sexo, LocalDate dataNascimento,
			Double salario, String grauEscolaridade, ObjectId idDepartamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, idDepartamento);
		
		this.grauEscolaridade = grauEscolaridade;
	}
	
	
	public String getGrauEscolaridade() {
		return grauEscolaridade;
	}

	public void setGrauEscolaridade(String grauEscolaridade) {
		this.grauEscolaridade = grauEscolaridade;
	}

	@Override
	public String toJson() {
		String json = "{\"grauEscolaridade\" : \""+grauEscolaridade+"\","
					+ "\"nome\" : \""+nome+"\", "
					+ "\"endereco\" : \""+endereco+"\", "
					+ "\"sexo\" : \""+sexo+"\", "
					+ "\"dataNascimento\" : \""+dataNascimento+"\", "
					+ "\"idDepartamento\" : \""+idDepartamento+"\", "
					+ "\"salario\" : \""+salario+"\"}";
		
		return json;
	}

	public static Secretario fromDocument(Document doc) {
		
		if(doc == null) return null;
		
		Secretario Secretario = new Secretario();
		
		Secretario.id = doc.getObjectId("_id");
		Secretario.nome = doc.getString("nome");
		Secretario.endereco = doc.getString("endereco");
		Secretario.sexo  = doc.getString("sexo");
		Secretario.grauEscolaridade  = doc.getString("grauEscolaridade");
		Secretario.dataNascimento = LocalDate.now();
		Secretario.salario = Double.parseDouble(doc.getString("salario"));
		Secretario.idDepartamento = new ObjectId(doc.getString("idDepartamento")); 
		
		return Secretario;
	}
	
}
