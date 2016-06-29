package pl.com.rest.builder;

import pl.com.rest.entity.PlaceEntityMongo;
import pl.com.rest.model.Place;

/**
 * Created by wewe on 29.06.16.
 */
public class PlaceEntityMongoBuilder {
    public static PlaceEntityMongo build(Place place, boolean active) {



        return new PlaceEntityMongo(place.getName(), place.getAddress(), place.getCity(), place.getOtherInformation(), active);
    }


    public static PlaceEntityMongo buildWithId(Place place, boolean active) {



        return new PlaceEntityMongo(place.getId(), place.getName(), place.getAddress(), place.getCity(), place.getOtherInformation(), active);


    }
}
