package org.example.BusinessLogic;

import org.example.BusinessLogic.Validators.ClientValidator;
import org.example.BusinessLogic.Validators.Validator;
import org.example.DataAccess.ClientDAO;
import org.example.Model.Client;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that contains the application logic for Client
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class ClientBLL {
    private Validator<Client> validator;
    private ClientDAO clientDAO;

    /**
     * Constructor which initializes the list of validators and the DAO used for
     * database operations
     */
    public ClientBLL(){
        this.validator = new ClientValidator();
        clientDAO = new ClientDAO();
    }

    /**
     * Method used tp find a client in the database using a given id
     * @param id of the client to be found
     * @return the client with the specified id
     * @throws NoSuchElementException if no client with the given id is found
     */
    public Client findClientById(int id){
        Client client = clientDAO.findById(id);
        if(client == null){
            throw new NoSuchElementException("A client with " + id + " id was not found!");
        }
        return client;
    }

    /**
     * Method used to validate and insert a new client in the database
     * @param client we want to insert
     */
    public void insertClient(Client client){
        validator.validate(client, 0);
        clientDAO.insert(client);
    }

    /**
     * Method used to return a list of all clients from the database
     * @return list of clients
     */
    public List<Client> findAllClients(){
        return clientDAO.findAll();
    }


    /**
     * Method used to delete a client using their id
     * @param id of the client
     */
    public void deleteClient(int id){
        clientDAO.delete(id);
    }

    /**
     * Method used to update a client from the database
     * @param client we want to update
     */
    public void updateClient(int id, Client client){
        validator.validate(client, id);
        clientDAO.update(id, client);
    }
}
