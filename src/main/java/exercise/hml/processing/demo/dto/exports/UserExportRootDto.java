package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;


@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportRootDto {

    @XmlElement(name = "user")
    private Set<UserExportDto> userExportDtos;

    public UserExportRootDto() {
    }

    public Set<UserExportDto> getUserExportDtos() {
        return userExportDtos;
    }

    public void setUserExportDtos(Set<UserExportDto> userExportDtos) {
        this.userExportDtos = userExportDtos;
    }
}
