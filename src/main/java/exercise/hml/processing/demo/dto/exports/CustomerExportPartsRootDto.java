package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportPartsRootDto {

    @XmlElement(name = "customer")
    private List<CustomerExportCarsDto> set;

    public CustomerExportPartsRootDto() {
    }

    public List<CustomerExportCarsDto> getSet() {
        return set;
    }

    public void setSet(List<CustomerExportCarsDto> set) {
        this.set = set;
    }
}
