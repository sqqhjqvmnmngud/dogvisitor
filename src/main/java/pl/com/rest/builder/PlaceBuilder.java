package pl.com.rest.builder;

import pl.com.rest.entity.PlaceEntityMongo;
import pl.com.rest.model.Place;

/**
 * Created by wewe on 29.06.16.
 */
public class PlaceBuilder {

    public static Place build(PlaceEntityMongo placeEntity, Object id){
        return new Place(id.toString(), placeEntity.getName(), placeEntity.getAddress(), placeEntity.getCity(), placeEntity.getOtherInformation());

    }
    public static Place build(PlaceEntityMongo placeEntity){
        return new Place(placeEntity.getId().toString(), placeEntity.getName(),placeEntity.getAddress(), placeEntity.getCity(), placeEntity.getOtherInformation());

    }


}
