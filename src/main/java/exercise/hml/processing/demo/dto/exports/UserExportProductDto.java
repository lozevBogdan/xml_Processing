package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportProductDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute
    private int age;

    @XmlElement(name = "sold-products")
    private List<SoldProductExporUserDto> list;

    public UserExportProductDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<SoldProductExporUserDto> getList() {
        return list;
    }

    public void setList(List<SoldProductExporUserDto> list) {
        this.list = list;
    }
}
