package exercise.hml.processing.demo.services;

import javax.xml.bind.JAXBException;

public interface CategoryService {

    void seedCategory() throws JAXBException;

    void getAllCategoriesByProductsCount() throws JAXBException;

}
