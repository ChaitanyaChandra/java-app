class Status
{
    public static void main(String args[])
    {

        URL url = new URL("localhost:8080");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        int code = connection.getResponseCode();

        System.out.println(code);
//        if (condition) {
//            // block of code to be executed if the condition is true
//        } else {
//            System.out.println("Hello, World");
//        }
    }
}