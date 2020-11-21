package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDto {

    @XmlElement(name = "car")
    private Set<CarExportDto> dtoCarSet;

    public CarExportRootDto() {
    }

    public Set<CarExportDto> getDtoCarSet() {
        return dtoCarSet;
    }

    public void setDtoCarSet(Set<CarExportDto> dtoCarSet) {
        this.dtoCarSet = dtoCarSet;
    }
}
