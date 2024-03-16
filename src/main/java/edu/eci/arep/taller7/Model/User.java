package edu.eci.arep.taller7.Model;

/**
 * model of an user in the system.
 * @author Santiago Forero Yate
 */
public class User {
    
    private String userName;
    private String  password;

    /**
     * Default constructor
     * 
     */
    public User(){

    }

    /**
     * default construcotr with parameters
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    

}
