package com.mauricio.model;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Trabalho extends Bean{

	private Integer horasSemanais;
	private ObjectId idPesquisador;
	private ObjectId idProjeto;
	
	public Trabalho() {
		super();
	}

	public Trabalho(ObjectId id, Integer horasSemanais, ObjectId idPesquisador, ObjectId idProjeto) {
		super(id);
		this.horasSemanais = horasSemanais;
		this.idPesquisador = idPesquisador;
		this.idProjeto = idProjeto;
	}

	public ObjectId getIdPesquisador() {
		return idPesquisador;
	}

	public void setIdPesquisador(ObjectId idPesquisador) {
		this.idPesquisador = idPesquisador;
	}

	public ObjectId getIdProjeto() {
		return idProjeto;
	}

	public void setIdProjeto(ObjectId idProjeto) {
		this.idProjeto = idProjeto;
	}

	public Integer getHorasSemanais() {
		return horasSemanais;
	}

	public void setHorasSemanais(Integer horasSemanais) {
		this.horasSemanais = horasSemanais;
	}

	@Override
	public String toJson() {
		String json = "{\"horasSemanais\" : \""+horasSemanais+"\", "
				+ "\"idPesquisador\" : \""+idPesquisador+"\", \"idProjeto\" : \""+idProjeto+"\"}";
		return json;
	}
	
	public static Trabalho fromDocument(Document doc) {
		if(doc == null) return null;
		
		Trabalho trabalho = new Trabalho();
		
		trabalho.id = doc.getObjectId("_id");
		trabalho.horasSemanais = Integer.parseInt(doc.getString("horasSemanais"));
		trabalho.idPesquisador = new ObjectId(doc.getString("idPesquisador")); 
		trabalho.idProjeto = new ObjectId(doc.getString("idProjeto")); 
		
		return trabalho;
	}
	
}
