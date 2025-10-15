package org.example.Model;

/**
 * Class used to create an object of type Client
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class Client {
    private int id;
    private String name;
    private String mail;

    /**
     * Constructor used to create a client with given attributes
     * @param id of the client
     * @param name of the client
     * @param mail of the client
     */
    public Client(int id, String name, String mail){
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    /**
     * Constructor used to create a client with given attributes without id
     * @param name of the client
     * @param mail of the client
     */
    public Client(String name, String mail){
        this.name = name;
        this.mail = mail;
    }

    /**
     * Constructor used to create an empty client object
     */
    public Client(){

    }

    /**
     * Method used to get the id of a client
     * @return the id of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Method used to set the id of a client
     * @param id we want to set for the client
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method used to get the name of a client
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Method used to set the name of a client
     * @param name we want to set for the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method used to get the mail of a client
     * @return the mail of the client
     */
    public String getMail() {
        return mail;
    }

    /**
     * Method used to set the mail of a client
     * @param mail we want to set for the client
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Method used to create a string with the client attributes
     * @return a string describing the client
     */
    public String toString(){
        return "id: " + this.id + " name: " + this.name + " mail: " + this.mail;
    }
}
