package exercise.hml.processing.demo.dto.exports;


import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoldProductExporUserDto {

    @XmlAttribute
    private int count;

    @XmlElement(name = "product")
    private List<ProductNameAndPriceExportDto> priceExportDtoList;

    public SoldProductExporUserDto() {
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductNameAndPriceExportDto> getPriceExportDtoList() {
        return priceExportDtoList;
    }

    public void setPriceExportDtoList(List<ProductNameAndPriceExportDto> priceExportDtoList) {
        this.priceExportDtoList = priceExportDtoList;
    }
}
