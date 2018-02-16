package org.mano.example;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;

@Stateless
public class CandidateFacade {

	private static final Logger LOGGER = Logger.getLogger(CandidateFacade.class.getName());
	//private final static String HOST = "localhost";
	private final static String HOST = System.getenv("MONGODB_SERVICE_HOST");
	//private final static String HOST = "mongodb";
	//private final static int PORT = 27017;
	private final static int PORT = Integer.decode(System.getenv("MONGODB_SERVICE_PORT"));

	//public final static String DATABASE = "cvbank";
	public final static String DATABASE = System.getenv("MONGODB_DATABASE");
	public final static String COLLECTION = "biodata";
	private final static String MONGODB_USER = System.getenv("MONGODB_USER");
	private final static String MONGODB_PASSWORD = System.getenv("MONGODB_PASSWORD");

	public MongoClient mongoClient() {
		MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());
		//return new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));
		return new MongoClient(HOST);

	}

	public void create(Candidate c) {
	MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());
	
      //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));
      MongoClient mongoClient = new MongoClient(HOST);
      MongoCollection<Document> collection =
         mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
      if  (c!=null) {

         Document d = new Document().append("id", c.getId())
            .append("skillSet", c.getSkillSet())
            .append("name", c.getName())
            .append("email", c.getEmail())
            .append("phone", c.getPhone())
            .append("gender", c.getGender())
            .append("lastDegree", c.getLastDegree())
            .append("lastDesig", c.getLastDesig())
            .append("expInYearMonth", c.getExpInYearMonth());
         collection.insertOne(d);
      }


   }

	public void update(Candidate c) {
	
		MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());

		@SuppressWarnings("deprecation")
		//MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));
		MongoClient mongoClient = new MongoClient(HOST);
		
		MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
		Document d = new Document();
		d.append("id", c.getId()).append("skillSet", c.getSkillSet()).append("name", c.getName())
				.append("email", c.getEmail()).append("phone", c.getPhone()).append("gender", c.getGender())
				.append("lastDegree", c.getLastDegree()).append("lastDesig", c.getLastDesig())
				.append("expInYearMonth", c.getExpInYearMonth());
		collection.updateOne(new Document("id", c.getId()), new Document("$set", d));
	}

	public void delete(Candidate c) {
		
		MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());

		//MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT), Arrays.asList(credential));
		MongoClient mongoClient = new MongoClient(HOST);
		MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
		collection.deleteOne(new Document("id", c.getId()));
	}

	public List<Candidate> find(String filter) {
		final List<Candidate> list = new ArrayList<>();
		MongoCredential credential = MongoCredential.createCredential(MONGODB_USER, DATABASE, MONGODB_PASSWORD.toCharArray());

		//MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT),Arrays.asList(credential) );
		MongoClient mongoClient = new MongoClient(HOST);
		MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
		FindIterable<Document> iter;
		if (filter == null || filter.trim().length() == 0) {
			iter = collection.find();
		} else {

			BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
			BsonDocument bsonDoc = new BsonDocument();
			bsonDoc.put("skillSet", bsonRegex);
			iter = collection.find(bsonDoc);

		}
		iter.forEach(new Block<Document>() {
			@Override
			public void apply(Document doc) {
				list.add(new Gson().fromJson(doc.toJson(), Candidate.class));
			}
		});
		
		return list;
	}
}
