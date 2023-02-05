package org.chaitu;
@SuppressWarnings("InfiniteLoopStatement")
public class Main {


    public static void main(String[] args) {

        String URL;

        String url_env = System.getenv("URL");

        if (url_env != null && !url_env.isEmpty()){
            URL = System.getenv("URL");
        }
        else {
            URL = "https://chaitu.net";
        }


        while (true) {
            Api myapi = new Api();
            System.out.println(myapi.call(URL));
        }


    }
}