package org.chaitu;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.*;


public class DBconn {
    private static DBconn instance;
    private MongoClient mongoClient;
    private MongoDatabase database;

    private MongoCollection<Document> collection;


    private DBconn() {

        String db_user = null;
        String db_pass = null;
        String db_url  = null;

        String db_user_env = System.getenv("DB_USER");
        String db_pass_env = System.getenv("DB_PASS");
        String db_url_env = System.getenv("DB_URL");

        if ((db_user_env != null && !db_user_env.isEmpty()) | (db_pass_env != null && !db_pass_env.isEmpty()) | (db_url_env != null && !db_url_env.isEmpty())){
            db_user = System.getenv("DB_USER");
            db_pass = System.getenv("DB_PASS");
            db_url = System.getenv("DB_URL");
        }
        else {
            db_user = "chaitu";
            db_pass = "123Chaitu";
            db_url = "cluster0.wdtudby.mongodb.net";
        }


        String uri = "mongodb+srv://" + db_user + ":" + db_pass + "@"+ db_url+ "/?retryWrites=true&w=majority";


        try {

            // Create Connection
            mongoClient = MongoClients.create(uri);
            System.out.println("MongoDB connected successfully");

            //Accessing the database
            database = mongoClient.getDatabase("status-db");
            System.out.println("MongoDB database connection ");

            //Accessing collection
            collection = database.getCollection("data");


        } catch (Exception e) {
            System.out.println("Database connection error! please check credentials");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static DBconn getInstance() {
        if (instance == null) {
            instance = new DBconn();
        }
        return instance;
    }

    public MongoDatabase getDatabase() {
        return database;
    }

}