package exercise.hml.processing.demo.dto.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportRootDto {


    @XmlElement(name = "part")
    private List<PartImportDto> partsDto;

    public PartImportRootDto() {
    }

    public List<PartImportDto> getPartsDto() {
        return partsDto;
    }

    public void setPartsDto(List<PartImportDto> partsDto) {
        this.partsDto = partsDto;
    }
}
