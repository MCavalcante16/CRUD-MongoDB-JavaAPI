package com.mauricio.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Pesquisador extends Funcionario{

	private String areaAtuacao;

	public Pesquisador() {
		
	}

	public Pesquisador(ObjectId id, String nome, String endereco, String sexo, LocalDate dataNascimento,
			Double salario, String areaAtuacao, ObjectId idDepartamento) {
		super(id, nome, endereco, sexo, dataNascimento, salario, idDepartamento);
		
		this.areaAtuacao = areaAtuacao;
	}
	
	

	public String getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(String areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}


	@Override
	public String toJson() {
		String json = "{\"areaAtuacao\" : \""+areaAtuacao+"\","
					+ "\"nome\" : \""+nome+"\", "
					+ "\"endereco\" : \""+endereco+"\", "
					+ "\"dataNascimento\" : \""+dataNascimento+"\", "
					+ "\"sexo\" : \""+sexo+"\", "
					+ "\"idDepartamento\" : \""+idDepartamento+"\", "
					+ "\"salario\" : \""+salario+"\"}";
		return json;
	}
	

	public static Pesquisador fromDocument(Document doc) {
		
		if(doc == null) return null;
		
		Pesquisador pesquisador = new Pesquisador();
		
		pesquisador.id = doc.getObjectId("_id");
		pesquisador.nome = doc.getString("nome");
		pesquisador.endereco = doc.getString("endereco");
		pesquisador.sexo  = doc.getString("sexo");
		pesquisador.areaAtuacao = doc.getString("areaAtuacao");
		pesquisador.salario = Double.parseDouble(doc.getString("salario"));
		pesquisador.dataNascimento = LocalDate.now();
		pesquisador.idDepartamento = new ObjectId(doc.getString("idDepartamento")); 
				
		return pesquisador;
	}


}
