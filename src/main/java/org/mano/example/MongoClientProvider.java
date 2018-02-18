package org.mano.example;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;



@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MongoClientProvider {

	private MongoClient mongoClient = null;
	
	private final static String MONGODB_USER = System.getenv("MONGODB_USER");
	private final static String MONGODB_PASSWORD = System.getenv("MONGODB_PASSWORD");
	public  final static String DATABASE = System.getenv("MONGODB_DATABASE");
	//public final static String DATABASE = "cvbank";
	private final static String HOST = System.getenv("MONGODB_SERVICE_HOST");
	//private final static String HOST = "localhost";
	
	private final static int PORT = Integer.decode(System.getenv("MONGODB_SERVICE_PORT"));
	//private final static int PORT = 27017;
	
	
	public MongoClientProvider() {		
		init();
	}
	

	@PostConstruct
	private void init() {
		
		MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());
		ServerAddress srvadr = new ServerAddress(HOST, PORT);
		List<MongoCredential> credentials = new ArrayList<MongoCredential>();
		credentials.add(credential);
		List<ServerAddress> seeds = new ArrayList<ServerAddress>();
		seeds.add(srvadr);
		
		try {
			
			mongoClient = new MongoClient(seeds, credentials);
			//mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Lock(LockType.READ)
	public MongoClient getMongoClient() {
		
		if (mongoClient == null) {
			init();
		}
		return mongoClient;
	}

}
