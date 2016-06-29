package pl.com.rest.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import pl.com.rest.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by wewe on 29.05.16.
 */
@Entity("places")
public class PlaceEntityMongo {


    @Id
    ObjectId id;

    @Property("name")
    private String name;

    @Property("address")
    private String address;

    @Property("city")
    private String city;

    @Property("otherInformation")
    private String otherInformation;

    @Reference
    private List<User> visitors;


    @Indexed
    private boolean active = false;


    public PlaceEntityMongo() {
    }

    public PlaceEntityMongo(String name, String address, String city, String otherInformation, List<User> visitors, boolean active) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.otherInformation = otherInformation;
        this.visitors = visitors;
        this.active = active;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public List<User> getVisitors() {
        return visitors;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
