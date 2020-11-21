package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportRootDto {

    @XmlElement(name = "supplier")
    private Set<SupplierExportDto> exportDtoSet;

    public SupplierExportRootDto() {
    }

    public Set<SupplierExportDto> getExportDtoSet() {
        return exportDtoSet;
    }

    public void setExportDtoSet(Set<SupplierExportDto> exportDtoSet) {
        this.exportDtoSet = exportDtoSet;
    }
}
