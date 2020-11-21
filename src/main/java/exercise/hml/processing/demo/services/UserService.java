package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface UserService {

    void seedUsers() throws JAXBException;

    void getAllSuccessfullySoldProducts() throws JAXBException;

    void getAllBySoldProductsSizeAndLastName() throws JAXBException;

}
