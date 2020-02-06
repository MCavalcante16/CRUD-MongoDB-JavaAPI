package com.mauricio.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map.Entry;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Dependente extends Bean{
	

	private String nome;
	private String sexo;
	private LocalDate dataNascimento;
	private String grauParentesco;
	private ObjectId idFuncionario;

	private Funcionario funcionario;
	
	public Dependente() {
		super();
	}

	public Dependente(ObjectId id, String nome, String sexo, LocalDate dataNascimento, String grauParentesco, ObjectId idFuncionario) {
		super(id);
		this.nome = nome;
		this.sexo = sexo;
		this.dataNascimento = dataNascimento;
		this.grauParentesco = grauParentesco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getGrauParentesco() {
		return grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
	public ObjectId getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(ObjectId idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	@Override
	public String toJson() {
		String json = "{\"nome\" : \""+nome+"\", "
				+ "\"sexo\" : \""+sexo+"\", "
				+ "\"dataNascimento\" :\""+dataNascimento+"\", "
				+ "\"idFuncionario\" : \""+idFuncionario+"\", "
				+ "\"grauParentesco\" : \""+grauParentesco+"\"}";
		return json;
	}

	public static Dependente fromDocument(Document doc) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		if(doc == null) return null;
		
		Dependente dependente = new Dependente();
		
		dependente.id = doc.getObjectId("_id");
		dependente.nome = doc.getString("nome");
		dependente.sexo = doc.getString("sexo");
		dependente.dataNascimento = LocalDate.parse(doc.getString("dataNascimento"), formatter);
		dependente.idFuncionario = new ObjectId(doc.getString("idFuncionario")); 
		dependente.grauParentesco = doc.getString("grauParentesco");
		return dependente;
	}
	
}
