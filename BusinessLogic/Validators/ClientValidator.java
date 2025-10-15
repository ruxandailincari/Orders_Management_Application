package org.example.BusinessLogic.Validators;

import org.example.DataAccess.ClientDAO;
import org.example.Model.Client;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Class used to validate the parameters for a client object
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class ClientValidator implements Validator<Client>{
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+._]+@(gmail.com|yahoo.com])$";

    /**
     * Method used to validate the client
     * @param client we want to validate
     * @param oldId parameter to check if the id was updated as well during an update operation
     */
    @Override
    public void validate(Client client, int oldId){

        ClientDAO clientDAO = new ClientDAO();
        List<Client> clients = clientDAO.findAll();

        boolean duplicate = clients.stream().anyMatch(c -> oldId != client.getId() && client.getId() == c.getId());
        if(duplicate){
            throw new IllegalArgumentException("A client with this id already exists!");
        }

       Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if(!(pattern.matcher(client.getMail()).matches())){
            throw new IllegalArgumentException("Invalid mail!");
        }
    }
}
