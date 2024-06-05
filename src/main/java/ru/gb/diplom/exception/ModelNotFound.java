package ru.gb.diplom.exception;

public class ModelNotFound extends RuntimeException{
    private static final String MESSAGE_IDPATTERN = "Entity %s with id %s was not found";
    private static final String MESSAGE_NAMEPATTERN = "Entity %s with name %s was not found";

    public ModelNotFound(String entityName, long id) {
        super(String.format(MESSAGE_IDPATTERN, entityName, id));
    }

    public ModelNotFound(String entityName, String name) {
        super(String.format(MESSAGE_NAMEPATTERN, entityName, name));
    }

}
