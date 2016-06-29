package pl.com.rest.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.com.rest.builder.PlaceBuilder;
import pl.com.rest.builder.PlaceEntityMongoBuilder;
import pl.com.rest.entity.PlaceEntityMongo;
import pl.com.rest.model.Place;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
                return PlaceBuilder.build(placeEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }


    public List<Place> getPlaces(){
        List<Place> lists = new ArrayList<>();
        for (PlaceEntityMongo placeEntity: getDatastore().find(PlaceEntityMongo.class)){
            lists.add(PlaceBuilder.build(placeEntity));
        }
        return lists;
    }

    public Place createPlace(Place user) {
        PlaceEntityMongo userEntity = PlaceEntityMongoBuilder.build(user,false);
        Key<PlaceEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return PlaceBuilder.build(userEntity, userEntityMongoKey.getId());

    }

    public void updatePlace(Place place){

        PlaceEntityMongo placeDb = PlaceEntityMongoBuilder.build(place, false);
        Query<PlaceEntityMongo> updateQuery = getDatastore().createQuery(PlaceEntityMongo.class).field("_id").equal(new ObjectId(place.getId()));
        UpdateOperations<PlaceEntityMongo> ops = getDatastore().createUpdateOperations(PlaceEntityMongo.class).set("name", placeDb.getName()).set("address", placeDb.getAddress()).set("city", placeDb.getCity())
                .set("visitors", placeDb.getOtherInformation()).set("otherInformation", placeDb.getOtherInformation());
        final UpdateResults results = getDatastore().update(updateQuery, ops);
        System.out.println(results.getWriteResult());


    }

    public void deletePlace(String id){
        getDatastore().delete(PlaceEntityMongo.class, new ObjectId(id));
    }

}
