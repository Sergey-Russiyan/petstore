package starter.entity;

import com.google.gson.JsonObject;
import lombok.Data;
import starter.utils.RandomUtils;
import java.util.logging.Logger;

@Data
public class UserTestObject {

    Logger log = Logger.getLogger(UserTestObject.class.getName());

    private String randomJibrish = new RandomUtils().getLoremIpsum();
    private int randomAnswerForEverything = new RandomUtils().getRand();

    public int id;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String phone;
    public int userStatus;

    public UserTestObject(){
        this.id = randomAnswerForEverything;
        this.username = "name" + randomJibrish;
        this.firstName = "firstName" + randomJibrish;
        this.lastName = "lastName" + randomJibrish;
        this.email = randomJibrish +"@fake.mail";
        this.password = "s0Me_pa5S!" + randomJibrish;
        this.phone = "0661234567" + randomAnswerForEverything;
        this.userStatus = randomAnswerForEverything;
    }
    public String asFlatJson(){
        return new com.google.gson.Gson().toJson(asString());
    }

    private String ascCollection(String outerBorder, int quantity){
        isUsersQuantityValid(quantity);
        StringBuilder build = new StringBuilder().append(outerBorder, 0, 1);
        for(int i = 0; i <= quantity;){
            UserTestObject u = new UserTestObject();
            build.append(u.asFlatJson());
            build.append(",");
            i++;
        }
        build.setLength(build.length() - 1);//remove lat coma
        build.append(outerBorder.substring(1));
        return new com.google.gson.Gson().toJson(build);
    }
    public String asArrayOf(int quantity){
        return ascCollection("[]", quantity);
    }
    public String asListOf(int quantity){
        return ascCollection("<>", quantity);
    }

    private boolean isUsersQuantityValid(int quantity){
        boolean result = false;
        if(quantity < 1 || quantity > 10000) { //some biz rules based limits
            log.info("Invalid quantity of users has been provided: " + quantity);
            //todo throw custom exception like InvalidTestDataException (no time for that right now)
            result = true;
        }
        return result;
    }

    public String asString(){
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
