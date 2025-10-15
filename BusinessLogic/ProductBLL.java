package org.example.BusinessLogic;

import org.example.BusinessLogic.Validators.ProductValidator;
import org.example.BusinessLogic.Validators.Validator;
import org.example.DataAccess.ProductDAO;
import org.example.Model.Product;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class that contains the application logic for Product
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class ProductBLL {
    private Validator<Product> validator;
    private ProductDAO productDAO;

    /**
     * Constructor which initializes the list of validators and the DAO used for
     * database operations
     */
    public ProductBLL(){
        validator = new ProductValidator();
        productDAO = new ProductDAO();
    }

    /**
     * Method used tp find a product in the database using a given id
     * @param id of the product to be found
     * @return the product with the specified id
     * @throws NoSuchElementException if no product with the given id is found
     */
    public Product findProductById(int id){
        Product product = productDAO.findById(id);
        if(product == null){
            throw new NoSuchElementException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * Method used to validate and insert a new product in the database
     * @param product we want to insert
     * @return the inserted object
     */
    public Product insertProduct(Product product){
        validator.validate(product, -1);
        return productDAO.insert(product);
    }

    /**
     * Method used to return a list of all products from the database
     * @return list of products
     */
    public List<Product> findAllProducts(){
        return productDAO.findAll();
    }

    /**
     * Method used to delete a product using their id
     * @param id of the product
     */
    public void deleteProduct(int id){
        productDAO.delete(id);
    }

    /**
     * Method used to update a product from the database
     * @param product we want to update
     */
    public void updateProduct(int id, Product product){
        validator.validate(product, id);
        productDAO.update(id, product);
    }
}
