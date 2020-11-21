package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarsWithPartsExportRootDto {


    @XmlElement(name = "car")
    private Set<CarExportsWithPartsDto> set;

    public CarsWithPartsExportRootDto() {
    }

    public Set<CarExportsWithPartsDto> getSet() {
        return set;
    }

    public void setSet(Set<CarExportsWithPartsDto> set) {
        this.set = set;
    }
}
