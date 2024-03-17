package edu.eci.arep.taller7;

import static spark.Spark.*;

import edu.eci.arep.taller7.autentication.Autenticator;
import edu.eci.arep.taller7.persistence.MapDb;

/**
 * first Secured app
 * 
 * @author Santiago Forero Yate
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            Thread.sleep(35000);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("An error: "+e.getMessage());
            Thread.currentThread().interrupt();
        }
        secure("certifications/ecikeystore.p12", "123456", null, null); 
        port(getPort());
        staticFiles.location("/public");
        get("/hello", (req, res) -> "Hello World");
        get("/loginservice", (req, res) -> {
            String name = req.queryParams("send"), pwd = req.queryParams("pd");
            if(Autenticator.getInstance().authenticate(name, pwd)){
                res.redirect("/welcome?user=" + name);
            } else {
                return "Invalid  User or Password, Please verify";
            }

            return null;
        });
        get("/welcome", (req, res) -> {
            String username = req.queryParams("user");
            return "<h1>Welcome " + username + "!</h1>";
        });

        MapDb.getInstance().insertProofs();
        
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
