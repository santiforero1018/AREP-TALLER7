package edu.eci.arep.taller7.autentication;

import java.security.MessageDigest;

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
        return MapDb.getUserFromDb(user, hashPwd(password)).isPresent();
    }

    /**
     * method that returns the hash of a chain
     * @param chain String to get the hash
     * @return a hash of the initial chain on String format
     */
    public static String hashPwd(String chain){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            System.out.println("something happened trying to encrypt: "+e.getMessage());
            return null;
        }

        byte[] hash =  md.digest(chain.getBytes());
        StringBuffer  sb = new StringBuffer();
        for(byte b : hash) {        
            sb.append(String.format("%02x", b));
        }
            
        return sb.toString();
    }

    public static Autenticator getInstance(){
        return _instance;
    }
}
