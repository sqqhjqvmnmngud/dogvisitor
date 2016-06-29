package pl.com.rest.builder;

import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.User;

/**
 * Created by wewe on 29.06.16.
 */
public class UserEntityMongoBuilder {

    public static  UserEntityMongo build(User user, boolean active){
        return new UserEntityMongo(user.getName(), user.getEmail(), user.getPassword(), user.getVisitedPlaces(), active);
    }

}
