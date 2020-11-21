package exercise.hml.processing.demo.dto.exports;

import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserRootDtoInfo {

    @XmlAttribute
    private int count;

    @XmlElement(name = "user")
    private List <UserExportProductDto> userList;

    public UserRootDtoInfo() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UserExportProductDto> getUsers() {
        return userList;
    }

    public void setUsers(List<UserExportProductDto> users) {
        this.userList = users;
    }
}
