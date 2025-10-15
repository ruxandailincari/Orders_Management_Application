package org.example.BusinessLogic.Validators;

/**
 * Interface used to define a validate method
 * @param <T> type of the object we want to validate
 * @author Ilincari Ruxanda
 * @since May 2025
 */
public interface Validator<T> {
    /**
     * definition of a validation method
     * @param t the object that needs to be validated
     * @param oldId the id of the object
     */
    void validate(T t, int oldId);
}
