package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface CustomerService {

    void seedCustomers() throws JAXBException;

    void getAllCustomersOrderByBirthDateAndOldestDriver() throws JAXBException;

    void getTotalSalesByCustomers() throws JAXBException;
}
