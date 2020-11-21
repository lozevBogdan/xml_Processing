package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;


@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductExportUserDto {

    @XmlElement
    private String name;
    @XmlElement
    private BigDecimal price;
    @XmlElement(name = "buyer-first-name")
    private String BuyerFirstName;
    @XmlElement(name = "buyer-last-name")
    private String BuyerLastName;

    public ProductExportUserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return BuyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        BuyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return BuyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        BuyerLastName = buyerLastName;
    }
}
