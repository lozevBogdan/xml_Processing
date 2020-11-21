package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface ProductService {

    void seedProducts() throws JAXBException;

    void getProductInRange500And1000();

}
