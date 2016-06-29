package pl.com.rest.builder;

import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.User;

/**
 * Created by wewe on 29.06.16.
 */
public class UserBuilder {
    public static User build(UserEntityMongo userEntity, Object id){
        return new User(id.toString(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), userEntity.getVisitedPlaces(), userEntity.getDogs());
    }

    public static User build(UserEntityMongo userEntity){
        return new User(userEntity.getId().toString(), userEntity.getName(),userEntity.getEmail(), userEntity.getPassword(), userEntity.getVisitedPlaces(), userEntity.getDogs());
    }
}
