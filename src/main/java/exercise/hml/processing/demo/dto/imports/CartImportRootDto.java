package exercise.hml.processing.demo.dto.imports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CartImportRootDto {

    @XmlElement(name = "car")
    private List<CarImportDto> casDto;

    public CartImportRootDto() {
    }

    public List<CarImportDto> getCasDto() {
        return casDto;
    }

    public void setCasDto(List<CarImportDto> casDto) {
        this.casDto = casDto;
    }
}
