package pl.com.rest.model;

/**
 * Created by wewe on 10.05.16.
 */
public class Place {
    private String id;
    private String name;
    private String address;
    private String city;
    private String otherInformation;
    private String ownerId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Place() {
    }

    public Place(String id, String name, String address, String city, String otherInformation, String ownerId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.otherInformation = otherInformation;
        this.ownerId = ownerId;
    }
}
