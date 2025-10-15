package org.example.BusinessLogic.Validators;

import org.example.DataAccess.ProductDAO;
import org.example.Model.Product;

import java.util.List;

/**
 * Class used to validate the parameters for a product object
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public class ProductValidator implements Validator<Product>{

    /**
     * Method used to validate the product
     * @param product we want to validate
     * @param oldId parameter to check if the id was updated as well during an update operation
     */
    @Override
    public void validate(Product product, int oldId) {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.findAll();
        boolean duplicate = products.stream().anyMatch(p -> p.getId() == product.getId() && p.getId() != oldId );
        if(duplicate){
            throw new IllegalArgumentException("A product with this id already exists!");
        }
        if(product.getPrice() < 0){
            throw new IllegalArgumentException("Price must pe a positive number!");
        }
        if(product.getStock() < 0){
            throw new IllegalArgumentException("Stock must pe a positive integer!");
        }
    }
}
