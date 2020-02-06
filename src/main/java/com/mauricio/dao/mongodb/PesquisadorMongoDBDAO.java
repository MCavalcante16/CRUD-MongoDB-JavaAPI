package com.mauricio.dao.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mauricio.dao.DependenteDAO;
import com.mauricio.dao.PesquisadorDAO;
import com.mauricio.model.Dependente;
import com.mauricio.model.Pesquisador;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoIterable;

public class PesquisadorMongoDBDAO extends FuncionarioMongoDBDAO<Pesquisador> implements PesquisadorDAO {

	public PesquisadorMongoDBDAO() {
		super("pesquisadores");
	}
	
	@Override
	public Pesquisador find(Object id) {	
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id.toString()));
	    Document doc = collection.find(query).first();
	    return Pesquisador.fromDocument(doc);
	}
	
	@Override
	public List<Pesquisador> findAll() {
		List<Pesquisador> Pesquisadores = new ArrayList<Pesquisador>();
		MongoIterable<Document> docs = collection.find();
		for(Document doc : docs) {
			Pesquisadores.add(Pesquisador.fromDocument(doc));
		}
		return Pesquisadores;
	}

}

