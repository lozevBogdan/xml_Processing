package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface CarService {

    void seedCars() throws JAXBException;

    void getCarsByToyota() throws JAXBException;

    void getCarsWithTheirParts() throws JAXBException;

}
