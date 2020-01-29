package starter.entity;

import com.google.gson.JsonObject;
import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class UserTestObject {

    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

    public UserTestObject(){
        this.id = 0;
        this.username = "name";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.email = "test@fake.mail";
        this.password = "s0Me_pa5S!";
        this.phone = "0661234567";
        this.userStatus = 0;
    }
    public String asJson(){
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("id", this.id);
        innerObject.addProperty("username", this.username);
        innerObject.addProperty("lastName", this.lastName);
        innerObject.addProperty("email", this.email);
        innerObject.addProperty("password", this.password);
        innerObject.addProperty("phone", this.phone);
        innerObject.addProperty("userStatus", this.userStatus);
        return innerObject.toString();
    }

}
