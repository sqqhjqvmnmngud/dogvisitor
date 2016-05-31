package pl.com.rest.entity;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Property("favouritePlaces")
    private List<Place> favouritePlaces;

    @Indexed
    private boolean active = false;
//
//    //Lifecycle methods -- Pre/PostLoad, Pre/PostPersist...
//    @PostLoad
//    private void postLoad(DBObject dbObj) {
//        LOGGER.info("postLoad: {}", dbObj);
//    }

    public UserEntityMongo() {
    }

    public UserEntityMongo(String name, boolean active){
        this.name = name;
        this.active = active;
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Place> getFavouritePlaces() {
        return favouritePlaces;
    }

    public boolean isActive() {
        return active;
    }
}
