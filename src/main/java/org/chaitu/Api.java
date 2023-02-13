package org.chaitu;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.IOException;
import java.net.MalformedURLException;

public class Api {
    public String call(String end_url) {

        // timestamp
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        String time_stamp = formatter.format(new Date());
        String APP_VERSION = System.getenv("APP_VERSION");
        String ENV = System.getenv("ENV");

        try {
            URL url = new URL(end_url);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println("Response Code: " + responseCode);


            postDB postdb = new postDB();
            if (responseCode >= 200 && responseCode <= 299) {
                postdb.call(time_stamp, responseCode, null, APP_VERSION, ENV);
                Thread.sleep(600000);
            }
            else {
                postdb.call(time_stamp, responseCode, null, APP_VERSION, ENV);
                Thread.sleep(60000);
            }

        } catch (MalformedURLException e) {
            postDB postdb = new postDB();
            postdb.call(time_stamp, 1, "Error: The URL is malformed.", APP_VERSION, ENV);
            System.out.println("Error: The URL is malformed.");
            System.exit(0);
        } catch (IOException e) {
            postDB postdb = new postDB();
            postdb.call(time_stamp, 1, "Error: Could not connect to localhost.", APP_VERSION, ENV);
            System.out.println("Error: Could not connect to localhost.");
            System.exit(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "API completed";
    }


}
