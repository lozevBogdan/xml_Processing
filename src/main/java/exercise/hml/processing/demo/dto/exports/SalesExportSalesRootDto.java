package exercise.hml.processing.demo.dto.exports;


import javax.xml.bind.annotation.*;
import java.util.Set;
import java.util.*;

@XmlRootElement(name = "sales")
@XmlAccessorType(XmlAccessType.FIELD)
public class SalesExportSalesRootDto {

    @XmlElement(name = "sale")
    private Set<SaleExportSalesDto>dtoSet;

    public SalesExportSalesRootDto() {
    }

    public Set<SaleExportSalesDto> getDtoSet() {
        return dtoSet;
    }

    public void setDtoSet(Set<SaleExportSalesDto> dtoSet) {
        this.dtoSet = dtoSet;
    }
}
