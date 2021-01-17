import java.util.Iterator;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;

public class databaseManager {
	public String get_json(String connId, String DB, String coll, String Qkey, String Qval){
		String jsonstr;
		ConnectionString uri = new ConnectionString(connId);
		MongoClientSettings settings = MongoClientSettings.builder()
    		    .applyConnectionString(uri)
    		    .retryWrites(true)
    		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(DB);  
        MongoCollection<Document> collection = database.getCollection(coll);
        FindIterable<Document> docs = collection.find(Filters.eq(Qkey,Qval));
        Iterator<Document> iter = docs.iterator();
		jsonstr=iter.next().toJson();
		mongoClient.close();
		return jsonstr;
	}
	
	public Document get_bson(String connId, String DB, String coll, String Qkey, String Qval){
		Document bsondoc;
		ConnectionString uri = new ConnectionString(connId);
		MongoClientSettings settings = MongoClientSettings.builder()
    		    .applyConnectionString(uri)
    		    .retryWrites(true)
    		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(DB);  
        MongoCollection<Document> collection = database.getCollection(coll);
        FindIterable<Document> docs = collection.find(Filters.eq(Qkey,Qval));
        Iterator<Document> iter = docs.iterator();
		bsondoc=iter.next();
		mongoClient.close();
		return bsondoc;
	}
	
	public Document set(String connId, String DB, String coll, String Qkey, String Qval, String Ukey, String Uval){
		ConnectionString uri = new ConnectionString(connId);
		MongoClientSettings settings = MongoClientSettings.builder()
    		    .applyConnectionString(uri)
    		    .retryWrites(true)
    		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(DB);  
        MongoCollection<Document> collection = database.getCollection(coll);
        Bson filter=new Document(Qkey, Qval);
        Bson newval=new Document(Ukey, Uval);
        Bson updater=new Document("$set", newval);
        collection.updateOne(filter, updater);
        mongoClient.close();
		return null;
	}
	public Document set(String connId, String DB, String coll, String Qkey, String Qval, String Ukey, int Uval){
		ConnectionString uri = new ConnectionString(connId);
		MongoClientSettings settings = MongoClientSettings.builder()
    		    .applyConnectionString(uri)
    		    .retryWrites(true)
    		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(DB);  
        MongoCollection<Document> collection = database.getCollection(coll);
        Bson filter=new Document(Qkey, Qval);
        Bson newval=new Document(Ukey, Uval);
        Bson updater=new Document("$set", newval);
        collection.updateOne(filter, updater);
        mongoClient.close();
		return null;
	}
	public Document set(String connId, String DB, String coll, String Qkey, String Qval, String Ukey, int[] Uval){
		ConnectionString uri = new ConnectionString(connId);
		MongoClientSettings settings = MongoClientSettings.builder()
    		    .applyConnectionString(uri)
    		    .retryWrites(true)
    		    .build();
		MongoClient mongoClient = MongoClients.create(settings);
		MongoDatabase database = mongoClient.getDatabase(DB);  
        MongoCollection<Document> collection = database.getCollection(coll);
        Bson filter=new Document(Qkey, Qval);
        Bson newval=new Document(Ukey, Uval);
        Bson updater=new Document("$set", newval);
        collection.updateOne(filter, updater);
        mongoClient.close();
		return null;
	}
}
