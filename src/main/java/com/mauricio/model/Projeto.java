package com.mauricio.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Projeto extends Bean{

	private String nome;
	private String periodoTempo;
	private ObjectId idDepartamento;
	
	public Projeto() {
		super();
	}

	public Projeto(ObjectId id, String nome, String periodoTempo, ObjectId idDepartamento) {
		super(id);
		this.nome = nome;
		this.periodoTempo = periodoTempo;
		this.idDepartamento = idDepartamento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPeriodoTempo() {
		return periodoTempo;
	}

	public void setPeriodoTempo(String periodoTempo) {
		this.periodoTempo = periodoTempo;
	}

	public ObjectId getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(ObjectId idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@Override
	public String toJson() {
		String json = "{\"nome\" : \""+nome+"\", \"idDepartamento\":\""+idDepartamento+"\", \"periodoTempo\" : \""+periodoTempo+"\"}";
		return json;
	}
	
	public static Projeto fromDocument(Document doc) {
		if(doc == null) return null;
		
		Projeto projeto = new Projeto();
		
		projeto.id = doc.getObjectId("_id");
		projeto.nome = doc.getString("nome");
		projeto.periodoTempo = doc.getString("periodoTempo");
		projeto.idDepartamento = new ObjectId(doc.getString("idDepartamento")); 
		
		return projeto;
	}
	
}
