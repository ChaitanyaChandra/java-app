package status.chaitu.net;
public class Api {
    public String sayHello() {

        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();
        return code;
    }
}