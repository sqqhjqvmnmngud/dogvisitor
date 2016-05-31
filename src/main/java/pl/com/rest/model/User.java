package pl.com.rest.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wewe on 26.04.16.
 */
public class User {

    private String id;
    @NotBlank
    @Size(min = 1 , max = 40)
    private String name;
    private List<Place> favouritePlaces = new ArrayList<>();

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public User(){

    }

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

    public List<Place> getFavouritePlaces() {
        return favouritePlaces;
    }

    public void setFavouritePlaces(List<Place> favouritePlaces) {
        this.favouritePlaces = favouritePlaces;
    }
}
