package com.mauricio.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mauricio.dao.TrabalhoDAO;
import com.mauricio.dao.UserDAO;
import com.mauricio.model.Trabalho;
import com.mauricio.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

public class TrabalhoMongoDBDAO extends GenericMongoDBDAO<Trabalho> implements TrabalhoDAO {

	public TrabalhoMongoDBDAO() {
		super("trabalhos");
	}
	
	@Override
	public Trabalho find(Object id) {	
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id.toString()));
	    Document doc = collection.find(query).first();
	    return Trabalho.fromDocument(doc);
	}
	
	@Override
	public List<Trabalho> findAll() {
		List<Trabalho> Trabalhos = new ArrayList<Trabalho>();
		MongoIterable<Document> docs = collection.find();
		for(Document doc : docs) {
			Trabalhos.add(Trabalho.fromDocument(doc));
		}
		return Trabalhos;
	}

}

