package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface SupplierService {

    public void seedSuppliers() throws JAXBException;

    void getLocalSuppliers() throws JAXBException;

}
