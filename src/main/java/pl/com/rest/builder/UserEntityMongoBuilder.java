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
public class UserEntityMongoBuilder {

    public static  UserEntityMongo build(User user, boolean active){
        List<PlaceEntityMongo> placeEntityList = new ArrayList<>();
        if (user.getVisitedPlaces() != null){

            for( Place place: user.getVisitedPlaces()){
                placeEntityList.add(PlaceEntityMongoBuilder.buildWithId(place,false));
            }
        }


        return new UserEntityMongo(user.getName(), user.getEmail(), user.getPassword(), placeEntityList, active);
    }

}
