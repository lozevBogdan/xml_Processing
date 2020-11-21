package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootExportDto {


    @XmlElement(name = "category")
    private Set<CategoryExportDto> categoryExportDtos;

    public CategoryRootExportDto() {
    }

    public Set<CategoryExportDto> getCategoryExportDtos() {
        return categoryExportDtos;
    }

    public void setCategoryExportDtos(Set<CategoryExportDto> categoryExportDtos) {
        this.categoryExportDtos = categoryExportDtos;
    }
}
