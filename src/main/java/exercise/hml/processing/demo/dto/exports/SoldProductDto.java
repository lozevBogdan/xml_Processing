package exercise.hml.processing.demo.dto.exports;


import com.sun.xml.bind.XmlAccessorFactory;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductDto {

    @XmlElement(name = "product")
    private List<ProductExportUserDto> products;


    public SoldProductDto() {
    }

    public List<ProductExportUserDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductExportUserDto> products) {
        this.products = products;
    }
}
