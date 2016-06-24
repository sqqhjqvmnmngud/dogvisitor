package pl.com.rest.model;

import javax.validation.constraints.NotNull;

/**
 * Created by wewe on 10.05.16.
 */
public class Place {
    @NotNull
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String address;
    
    @NotNull
    private String city;
    private String otherInformation;


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



    public Place() {
    }

    public Place(String id, String name, String address, String city, String otherInformation) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.otherInformation = otherInformation;

    }
}
