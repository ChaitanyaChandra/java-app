package org.chaitu;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class postDB {
    private DBconn mongoDBConnection;
    private MongoCollection<Document> collection;


    public void call(String TimeStamp, int StatusCode, String raw_status) {
        try {

            if (StatusCode >= 200 && StatusCode <= 299) {
                raw_status = "OK";
            }
            else if (StatusCode >= 300 && StatusCode <= 399) {
                raw_status = "Notice";
            }
            else if (StatusCode >= 400 && StatusCode <= 499) {
                raw_status = "Warning";
            }
            else if (StatusCode >= 500 && StatusCode <= 599) {
                raw_status = "Error";
            }

            Document document = new Document("TimeStamp", TimeStamp)
                    .append("front_end", new Document("status code", StatusCode)
                            .append("status", raw_status)
                            .append("version", "1.0"));

            mongoDBConnection = DBconn.getInstance();
            collection = mongoDBConnection.getDatabase().getCollection("data");
            collection.insertOne(document);
            System.out.println("Data Inserted");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}