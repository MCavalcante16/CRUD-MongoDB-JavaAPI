package com.mauricio.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mauricio.dao.DependenteDAO;
import com.mauricio.model.Dependente;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

public class DependenteMongoDBDAO extends GenericMongoDBDAO<Dependente> implements DependenteDAO {

	public DependenteMongoDBDAO() {
		super("dependentes");
	}
	
	@Override
	public Dependente find(Object id) {	
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id.toString()));
	    Document doc = collection.find(query).first();
	    return Dependente.fromDocument(doc);
	}
	
	@Override
	public List<Dependente> findAll() {
		List<Dependente> dependentes = new ArrayList<Dependente>();
		MongoIterable<Document> docs = collection.find();
		for(Document doc : docs) {
			dependentes.add(Dependente.fromDocument(doc));
		}
		return dependentes;
	}

}

