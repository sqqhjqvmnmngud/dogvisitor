package pl.com.rest.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wewe on 26.04.16.
 */
public class User {

    private String id;
    @NotNull
    @Size(min = 1 , max = 40)
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;
    private List<Place> favouritePlaces = new ArrayList<>();
    private List<Dog> dogs = new ArrayList<>();

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, List<Place> favouritePlaces, List<Dog> dogs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.favouritePlaces = favouritePlaces;
        this.dogs = dogs;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Dog> getDogs() {
        return dogs;
    }
}
