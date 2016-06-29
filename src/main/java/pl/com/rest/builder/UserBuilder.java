package pl.com.rest.builder;

import pl.com.rest.entity.PlaceEntityMongo;
import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.Place;
import pl.com.rest.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wewe on 29.06.16.
 */
public class UserBuilder {
    public static User build(UserEntityMongo userEntity, Object id){
        List<Place> placeList = new ArrayList<>();
        for( PlaceEntityMongo placeEntityMongo: userEntity.getVisitedPlaces()){
            placeList.add(PlaceBuilder.build(placeEntityMongo));
        }

        return new User(id.toString(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword(), placeList, userEntity.getDogs());
    }

    public static User build(UserEntityMongo userEntity){
        List<Place> placeList = new ArrayList<>();
        for( PlaceEntityMongo placeEntityMongo: userEntity.getVisitedPlaces()){
            placeList.add(PlaceBuilder.build(placeEntityMongo));
        }

        return new User(userEntity.getId().toString(), userEntity.getName(),userEntity.getEmail(), userEntity.getPassword(), placeList, userEntity.getDogs());
    }
}
