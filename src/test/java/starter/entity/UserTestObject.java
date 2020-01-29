package starter.entity;

import lombok.Data;

@Data
public class UserTestObject {

    public int id;
    public String userName;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

    public UserTestObject(){
        this.id = 0;
        this.userName = "name";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.email = "test@fake.mail";
        this.password = "s0Me_pa5S!";
        this.phone = "0661234567";
        this.userStatus = 0;
    }

}
