package pl.com.rest.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import pl.com.rest.entity.PlaceEntityMongo;
import pl.com.rest.model.Place;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by wewe on 29.05.16.
 */
public class PlaceDatabase extends MongoDB {
    public Place getPlace(String id) {
        try {
            PlaceEntityMongo placeEntity = getDatastore()
                    .find(PlaceEntityMongo.class)
                    .field("id")
                    .equal(new ObjectId(id))
                    .get();

            if (placeEntity != null) {
                return buildPlaceResponse(placeEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public Collection<Place> getPlaces(){
        Collection<Place> lists = new ArrayList<>();
        for (PlaceEntityMongo placeEntity: getDatastore().find(PlaceEntityMongo.class)){
            lists.add(buildPlaceResponse(placeEntity));
        }
        return lists;
    }

    public Place createPlace(Place user) {
        PlaceEntityMongo userEntity = buildPlaceEntity(user,false);
        Key<PlaceEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return buildPlaceResponse(userEntity, userEntityMongoKey.getId());

    }

    public void deletePlace(String id){
        getDatastore().delete(PlaceEntityMongo.class, new ObjectId(id));
    }


    private Place buildPlaceResponse(PlaceEntityMongo placeEntity, Object id){
        return new Place(id.toString(), placeEntity.getName(), placeEntity.getAddress(), placeEntity.getCity(), placeEntity.getOtherInformation());

    }
    private Place buildPlaceResponse(PlaceEntityMongo placeEntity){
        return new Place(placeEntity.getId().toString(), placeEntity.getName(),placeEntity.getAddress(), placeEntity.getCity(), placeEntity.getOtherInformation());

    }

    private PlaceEntityMongo buildPlaceEntity(Place place, boolean active){
        return new PlaceEntityMongo(place.getName(), place.getAddress(), place.getCity(), place.getOtherInformation(),  active);
    }







}
