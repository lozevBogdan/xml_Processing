package exercise.hml.processing.demo.dto.imports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.*;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierImportDto> supplierImportDtoList;


    public SupplierImportRootDto() {
    }

    public List<SupplierImportDto> getSupplierImportDtoList() {
        return supplierImportDtoList;
    }

    public void setSupplierImportDtoList(List<SupplierImportDto> supplierImportDtoList) {
        this.supplierImportDtoList = supplierImportDtoList;
    }
}
