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
public class PlaceEntityMongoBuilder {
    public static PlaceEntityMongo build(Place place, boolean active){

        List<UserEntityMongo> userEntityList = new ArrayList<>();
        if (place.getVisitors() != null){

            for( User user: place.getVisitors()){
                userEntityList.add(UserEntityMongoBuilder.build(user,false));
            }
        }


        return new PlaceEntityMongo(place.getName(), place.getAddress(), place.getCity(), place.getOtherInformation(),userEntityList, active);
    }


    public static PlaceEntityMongo buildWithId(Place place, boolean active){

        List<UserEntityMongo> userEntityList = new ArrayList<>();
        if (place.getVisitors() != null){

            for( User user: place.getVisitors()){
                userEntityList.add(UserEntityMongoBuilder.build(user,false));
            }
        }


        return new PlaceEntityMongo(place.getId(), place.getName(), place.getAddress(), place.getCity(), place.getOtherInformation(),userEntityList, active);




    }
}
