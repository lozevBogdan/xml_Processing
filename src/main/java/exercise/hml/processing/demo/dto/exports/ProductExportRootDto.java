package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductExportRootDto {

    @XmlElement(name = "product")
    private Set<ProductExportDto> products;

    public ProductExportRootDto() {
    }

    public Set<ProductExportDto> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductExportDto> products) {
        this.products = products;
    }
}
