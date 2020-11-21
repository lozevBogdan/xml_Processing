package exercise.hml.processing.demo.dto.imports;


import jdk.dynalink.linker.LinkerServices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportRoorDto {


    @XmlElement(name = "customer")
    private List<CustomerImportDto> customerImportDtos;

    public CustomerImportRoorDto() {
    }

    public List<CustomerImportDto> getCustomerImportDtos() {
        return customerImportDtos;
    }

    public void setCustomerImportDtos(List<CustomerImportDto> customerImportDtos) {
        this.customerImportDtos = customerImportDtos;
    }
}
