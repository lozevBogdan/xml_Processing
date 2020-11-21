package exercise.hml.processing.demo.dto.exports;


import java.util.*;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportDto {

    @XmlAttribute(name ="first-name")
    private String firstName;
    @XmlAttribute(name ="last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private SoldProductDto products;

    public UserExportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SoldProductDto getProducts() {
        return products;
    }

    public void setProducts(SoldProductDto products) {
        this.products = products;
    }
}
