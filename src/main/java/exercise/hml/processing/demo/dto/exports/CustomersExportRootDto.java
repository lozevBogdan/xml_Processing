package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersExportRootDto {


    @XmlElement(name = "customer")
    private Set<CustomerExportDto> customerExportDtos;

    public CustomersExportRootDto() {
    }

    public Set<CustomerExportDto> getCustomerExportDtos() {
        return customerExportDtos;
    }

    public void setCustomerExportDtos(Set<CustomerExportDto> customerExportDtos) {
        this.customerExportDtos = customerExportDtos;
    }
}
