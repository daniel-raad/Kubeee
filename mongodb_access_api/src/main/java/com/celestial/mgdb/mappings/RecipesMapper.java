package com.celestial.mgdb.mappings;

import java.util.ArrayList;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Service
public class RecipesMapper 
{
	final private	String	dbConnection = "127.0.0.1:27017";
	final private	String	MONGODB_ENDPOINT = "MONGODB_ENDPOINT"; 
	private MongoClient client;
	private MongoDatabase database;
	
	
	public	void	connectToDB()
	{
		String dbEnpoint = System.getenv().getOrDefault(MONGODB_ENDPOINT, dbConnection);
		if( client == null )
		{
			System.out.println("Connecting to: " + "mongodb://" + dbEnpoint);
			client = MongoClients.create("mongodb://" + dbEnpoint);
			database = client.getDatabase("cooker");
		}
	}
	
	public	ArrayList<String> getRecipesAsString()
	{
		MongoCollection<Document> recipes = database.getCollection("recipes");
		FindIterable<Document> cursor = recipes.find(); 
		ArrayList<String> recipesAsJson = new ArrayList<>();
		
		cursor.forEach((e)->{
			System.out.println(e.toJson());
			recipesAsJson.add(e.toString());
		});
		return recipesAsJson;
	}

}
