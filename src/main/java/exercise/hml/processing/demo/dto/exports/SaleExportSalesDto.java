package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleExportSalesDto {

    @XmlElement(name = "car")
    private CarSaleExportDto carSaleExportDto;

    @XmlElement(name = "customer-name")
    private String customerName;

    @XmlElement
    private double discount;

    @XmlElement
    private double price;


    @XmlElement(name = "price-with-discount")
    private double priceWithDiscount;



    public SaleExportSalesDto() {
    }

    public CarSaleExportDto getCarSaleExportDto() {
        return carSaleExportDto;
    }

    public void setCarSaleExportDto(CarSaleExportDto carSaleExportDto) {
        this.carSaleExportDto = carSaleExportDto;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
