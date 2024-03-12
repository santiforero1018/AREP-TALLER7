package edu.eci.arep.taller7;

import static spark.Spark.*;

/**
 * first Secured app
 * 
 * @author Santiago Forero Yate
 *
 */
public class App {
    public static void main(String[] args) {
        secure("certifications/ecikeystore.p12", "123456", null, null); 
        port(getPort());
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello World");
        get("/loginservice", (req, res) -> {
            String name = req.queryParams("send"), pwd = req.queryParams("pd");
            return null;
        });
    }

    /**
     * method that returns the env port, else, returns 5000
     * 
     * @return and integer that represents the port
     */
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000;
    }
}