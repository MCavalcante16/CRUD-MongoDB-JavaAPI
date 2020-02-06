package com.mauricio.dao.mongodb;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mauricio.dao.GenericDAO;
import com.mauricio.model.Bean;
import com.mauricio.mongodb.MongoDBUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;



public abstract class GenericMongoDBDAO<T extends Bean> implements GenericDAO<T> {

	protected MongoDatabase db;
	protected MongoCollection<Document> collection;
	
	public GenericMongoDBDAO(String tableName) {
		this.db = MongoDBUtil.getDatabase();
		this.collection = db.getCollection(tableName);
	}
	
	@Override
	public void insert(T t) {
		
		Document doc = t.toDocument();
		this.collection.insertOne(doc);
		t.setId((ObjectId)doc.get("_id"));
	}

	@Override
	public void update(T t) {
		Document update = new Document();
		update.append("$set", t.toDocument());
		
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(t.getId().toString()));
		
		collection.updateOne(query, update);
	}

	@Override
	public void delete(T t) {
		delete(t.getId());
	}


	@Override
	public void delete(Object id) {
		BasicDBObject query = new BasicDBObject();
	    query.put("_id", new ObjectId(id.toString()));
		collection.deleteOne(query);
	}

	
	@Override
	public void close() {
		MongoDBUtil.closeConnection(db);
	}
}
