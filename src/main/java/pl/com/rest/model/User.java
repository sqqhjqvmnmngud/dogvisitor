package pl.com.rest.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
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
    @Email
    private String email;

    @NotNull
    private String password;
    private List<Place>  visitedPlaces;

    private List<Dog> dogs = new ArrayList<>();

    public User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String id, String name, String email, String password, List<Place>  visitedPlaces, List<Dog> dogs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.visitedPlaces = visitedPlaces;
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

    public List<Place>  getVisitedPlaces() {
        return visitedPlaces;
    }

    public void setVisitedPlaces(List<Place>  visitedPlaces) {
        this.visitedPlaces = visitedPlaces;
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

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public boolean placeIsVistied(Place place){
        for(Place p : this.visitedPlaces){
            if(p.getId().contentEquals(place.getId()))
            return true;
        }
        return false;
    }
    public boolean deleteVisitedPlace(String placeId){
        int index=-1;
        for(Place p : this.visitedPlaces){
            if (p.getId().contentEquals(placeId)){
                index = visitedPlaces.indexOf(p);
            }
        }
        if(index!= -1){
            visitedPlaces.remove(index);
            return true;
        }
        else return false;
    }

    public boolean deleteDog(String dogName){

        int index = isDogExist(dogName);
        if(index!= -1){
            dogs.remove(index);
            return true;
        }
        else return false;
    }

    public int isDogExist(String dogName){
        for(Dog dog : this.dogs){
            if (dog.getName().contentEquals(dogName)){
                return dogs.indexOf(dog);
            }
        }
        return -1;
    }

    public void updateDog(Dog dog){
        int index = isDogExist(dog.getName());
        dogs.remove(index);
        dogs.add(dog);

    }
}
