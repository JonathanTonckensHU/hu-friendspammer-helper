package nl.hu.sie.bep.jonathan.friendspammer_helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoSaver {
	
	public static boolean saveEmail(Email email) {
		final Logger logger = LoggerFactory.getLogger(MongoSaver.class);
		String database = "friendspammer";
		
		boolean success = true;
		
		try (MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017))) {
			
			MongoDatabase db = mongoClient.getDatabase( database );
			
			MongoCollection<Document> c = db.getCollection("email");
			
			Document  doc = new Document ("to", email.getTo())
			        .append("from", email.getFrom())
			        .append("subject", email.getSubject())
			        .append("text", email.getText())
			        .append("asHtml", email.isAsHTML());
			c.insertOne(doc);
		} catch (MongoException mongoException) {
			logger.error("Error while saving to Mongo", mongoException);
			success = false;
		}
		
		return success;
 		
	}
	
	public static List<Email> getHistory() {	
		String database = "friendspammer";
		
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		
		MongoDatabase db = mongoClient.getDatabase(database);
		
		MongoCollection<Document> c = db.getCollection("email");
		
		Iterator<Document> it = c.find().iterator();
		
		ArrayList<Email> emails = new ArrayList<Email>();
		
		while(it.hasNext())
		{
			Document emailDoc = it.next();
			Email email = new Email(
				emailDoc.get("to").toString(),
				emailDoc.get("from").toString(),
				emailDoc.get("subject").toString(),
				emailDoc.get("text").toString(),
				(boolean)emailDoc.get("asHtml")
			);
			
			emails.add(email);
		}


		mongoClient.close();
		
		return emails;
	}

	private MongoSaver() {} //make sure it can't be initialized because there are only static methods
}
