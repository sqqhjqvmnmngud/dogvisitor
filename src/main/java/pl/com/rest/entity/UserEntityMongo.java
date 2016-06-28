package pl.com.rest.entity;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.com.rest.model.Dog;
import pl.com.rest.model.Place;

import java.util.List;

/**
 * Created by wewe on 28.05.16.
 */
@Entity("users")
public class UserEntityMongo {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserEntityMongo.class);

    @Id
    ObjectId id;

    @Property("name")
    private String name;

    @Property("visitedPlaces")
    private List<Place> visitedPlaces;

    @Property("email")
    private String email;

    @Property("password")
    private String password;

    @Property("dogs")
    private List<Dog> dogs ;
    private boolean active = false;


    public UserEntityMongo() {
    }

    public UserEntityMongo(String name, String email, String password, List<Place> visitedPlaces, boolean active){
        this.name = name;
        this.email = email;
        this.password = password;
        this.visitedPlaces = visitedPlaces;
        this.active = active;

    }


    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Place> getVisitedPlaces() {
        return visitedPlaces;
    }

    public boolean isActive() {
        return active;
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
