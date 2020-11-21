package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsRootDto {

    @XmlElement(name = "part")
    private Set<PartsExportDto> partsExportDtos;

    public PartsRootDto() {
    }

    public Set<PartsExportDto> getPartsExportDtos() {
        return partsExportDtos;
    }

    public void setPartsExportDtos(Set<PartsExportDto> partsExportDtos) {
        this.partsExportDtos = partsExportDtos;
    }
}
