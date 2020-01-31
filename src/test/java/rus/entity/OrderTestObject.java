package rus.entity;

import com.google.gson.JsonObject;
import lombok.Data;
import lombok.Getter;
import rus.utils.RandomUtils;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class OrderTestObject {

    private int uniq = new RandomUtils().getRand();
    public String creationTime = Instant.now().toString();

    public int id;
    public int petId;
    public int quantity;
    public String shipDate;
    public String status;
    public boolean complete;

    public OrderTestObject(){
        this.id = uniq;
        this.petId = uniq;
        this.quantity = uniq;
        this.quantity = uniq;
        this.shipDate = creationTime;
        this.status = OrderStatuses.PLACED.stat;
        this.complete = false;
    }

    public String asString(){
        JsonObject innerObject = new JsonObject();
        innerObject.addProperty("id", this.id);
        innerObject.addProperty("petId", this.petId);
        innerObject.addProperty("quantity", this.quantity);
        innerObject.addProperty("shipDate", this.shipDate);
        innerObject.addProperty("status", this.status);
        innerObject.addProperty("complete", this.complete);
        return innerObject.toString();
    }
    private String getRandomStatus(){
        List<String> allStats = Arrays.asList(OrderStatuses.PLACED.stat, OrderStatuses.APPROVED.stat, OrderStatuses.DELIVERED.stat);
        Collections.shuffle(allStats);
        return allStats.get(0);
    }

    @Getter
    public enum OrderStatuses {
        PLACED      ("placed"),
        APPROVED    ("approved"),
        DELIVERED   ("delivered"),
        ;
        private final String stat;

        OrderStatuses(String stat){
            this.stat = stat;
        }
    }

}
