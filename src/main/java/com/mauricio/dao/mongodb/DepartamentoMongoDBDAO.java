package com.mauricio.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mauricio.dao.DepartamentoDAO;
import com.mauricio.dao.UserDAO;
import com.mauricio.model.Departamento;
import com.mauricio.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

public class DepartamentoMongoDBDAO extends GenericMongoDBDAO<Departamento> implements DepartamentoDAO {

	public DepartamentoMongoDBDAO() {
		super("departamentos");
	}
	
	@Override
	public Departamento find(Object id) {	
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id.toString()));
	    Document doc = collection.find(query).first();
	    return Departamento.fromDocument(doc);
	}
	
	@Override
	public List<Departamento> findAll() {
		List<Departamento> departamentos = new ArrayList<Departamento>();
		MongoIterable<Document> docs = collection.find();
		for(Document doc : docs) {
			departamentos.add(Departamento.fromDocument(doc));
		}
		return departamentos;
	}

}

