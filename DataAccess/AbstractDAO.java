package org.example.DataAccess;

import org.example.Connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generic class used to define common methods for accessing the database for any
 * object of type T. The queries for accessing the DB for a specific object that
 * corresponds to a table will be generated dynamically through reflection
 * @param <T> type of the object for which we're accessing the database
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class AbstractDAO<T> {
    private final Class<T> type;

    /**
     * Constructor used to determine T during runtime
     */
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Method used to create a SELECT * FROM a certain field query
     * @param field for which we're doing the selection
     * @return a string of the query
     */
    private String createSelectQuery(String field){
        return "SELECT * FROM " +
                type.getSimpleName() +
                "WHERE " + field + " =?";
    }

    /**
     * Method used to find an object in the database based on id
     * @param id the identifier of the object
     * @return object of type T or null if the object wasn't found
     */
    public T findById(int id){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return createObjects(resultSet).getFirst();
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
        return null;
    }

    /**
     * Method used to create a list of objects T based on a given result set
     * @param resultSet of a query
     * @return list of type T objects
     */
    private List<T> createObjects(ResultSet resultSet){
        List<T> l = new ArrayList<>();
        Constructor cons[] = type.getDeclaredConstructors();
        Constructor c = Arrays.stream(cons).filter(constructor -> constructor.getGenericParameterTypes().length == 0).findFirst().orElse(null);
        try{
            while(resultSet.next()){
                assert c != null;
                c.setAccessible(true);
                T instance = (T) c.newInstance();
                Arrays.stream(type.getDeclaredFields()).forEach( field -> {
                    try {
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        Object value = resultSet.getObject(fieldName);
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    } catch (Exception e)	{
                        System.out.println(e.getMessage());
                    }
                });
                l.add(instance);}
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

    /**
     * Method used to insert an object of type T into the database
     * @param t the object we want to insert
     * @return the inserted object
     */
    public T insert(T t){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        List<Object> param = new ArrayList<>();
        String s = buildInsertQuery(t, param);

        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s);
            for(int i=0; i<param.size(); i++){
                preparedStatement.setObject(i+1, param.get(i));
            }
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
        return t;
    }

    /**
     * Method used to create an insert query
     * @param t the object we want to insert
     * @param param an empty list into which we will put the parameters
     * @return a string representing the insert query
     */
    private String buildInsertQuery(T t, List<Object> param){
        StringBuilder s = new StringBuilder("INSERT INTO `" + type.getSimpleName() + "` (");
        StringBuilder values = new StringBuilder("VALUES( ");

        Arrays.stream(type.getDeclaredFields()).forEach( field -> {
            field.setAccessible(true);
            if(field.getName().equals("id")){
                return;
            }
            s.append(field.getName()).append(",");
            values.append("?,");
            try{
                param.add(field.get(t));
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
        s.setLength(s.length() -1);
        values.setLength(values.length() - 1);
        s.append(")").append(values).append(")");
        return s.toString();
    }

    /**
     * Method used to return a list of all objects in a table
     * @return list of objects
     */
    public List<T> findAll(){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String s = "SELECT * FROM `" + type.getSimpleName() + "`";
        List<T> obj = new ArrayList<>();
        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            obj = createObjects(resultSet);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
        return obj;
    }

    /**
     * Method used to delete an object in the table from the database based on id
     * @param id of the object we want to delete
     */
    public void delete(int id){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        String s = "DELETE FROM `" + type.getSimpleName() + "` WHERE id = ?";
        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
    }

    /**
     * Method used to update an entry from the database
     * @param id of the object to be updated
     * @param t the updated object
     */
    public void update(int id, T t){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        StringBuilder s = new StringBuilder("UPDATE `" + type.getSimpleName() + "` SET ");
        List<Object> param = new ArrayList<>();
        Arrays.stream(type.getDeclaredFields()).forEach( field ->
        { field.setAccessible(true);
            s.append(field.getName()).append(" = ?, ");
            try{
                param.add(field.get(t));
            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        });
        s.setLength(s.length() - 2);
        s.append(" WHERE id = ?");
        param.add(id);

        try{
            con = ConnectionFactory.getConnection();
            preparedStatement = con.prepareStatement(s.toString());
            for(int i=0; i<param.size(); i++){
                preparedStatement.setObject(i+1, param.get(i));
            }
            preparedStatement.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally{
            ConnectionFactory.close(preparedStatement);
            ConnectionFactory.close(con);
        }
    }
}
