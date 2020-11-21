package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface SaleService {

    void seedSales();

    void getSalesWithAppliedDiscount() throws JAXBException;
}
