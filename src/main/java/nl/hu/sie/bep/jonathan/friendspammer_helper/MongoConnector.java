package nl.hu.sie.bep.jonathan.friendspammer_helper;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoConnector {
	public static MongoClient connect() {
		return new MongoClient(new ServerAddress("localhost", 27017));
	}
	private MongoConnector() {} //make sure it can't be initialized because there are only static methods
}
