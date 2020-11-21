package exercise.hml.processing.demo.dto.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportRootDto {


    @XmlElement(name = "product")
    private Set<ProductImportDto> productImportDtos;

    public ProductImportRootDto() {
    }

    public Set<ProductImportDto> getProductImportDtos() {
        return productImportDtos;
    }

    public void setProductImportDtos(Set<ProductImportDto> productImportDtos) {
        this.productImportDtos = productImportDtos;
    }
}
