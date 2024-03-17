package edu.eci.arep.taller7.autentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import edu.eci.arep.taller7.persistence.MapDb;
/**
 * Class to do all autentication actions
 * 
 * @author Santiago Forero Yate
 */
public class Autenticator {
    
    private static Autenticator _instance = getInstance();

    /**
     * method that authenticate an user
     * @param user  the user to be authenticated
     * @param password the  password of the user
     * @return  true if the user is valid, false otherwise
     */
    public static  boolean authenticate(String user, String password){
        System.out.println(hashPwd(password));
        return MapDb.getUserFromDb(user, hashPwd(password)).isPresent();
    }

    /**
     * method that returns the hash of a chain
     * @param chain String to get the hash
     * @return a hash of the initial chain on String format
     */
    public static String hashPwd(String chain){
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(chain.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
       
    }

    /**
     * return an unique instace of Autenticator class
     * @return  instance of Autenticator class
     */
    public static Autenticator getInstance(){
        return _instance;
    }
}
