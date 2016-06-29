package pl.com.rest.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.com.rest.builder.UserBuilder;
import pl.com.rest.builder.UserEntityMongoBuilder;
import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wewe on 29.05.16.
 */
public class UserDatabase extends MongoDB {

    public User getUser(String id) {
        try {
            UserEntityMongo userEntity = getDatastore()
                    .find(UserEntityMongo.class)
                    .field("id")
                    .equal(new ObjectId(id))
                    .get();

            if (userEntity != null) {
                return UserBuilder.build(userEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void updateUser(User user){

        UserEntityMongo userDb = UserEntityMongoBuilder.build(user, false);
        Query<UserEntityMongo> updateQuery = getDatastore().createQuery(UserEntityMongo.class).field("_id").equal(new ObjectId(user.getId()));
        UpdateOperations<UserEntityMongo> ops = getDatastore().createUpdateOperations(UserEntityMongo.class).set("name", userDb.getName()).set("email", userDb.getEmail()).set("password",userDb.getPassword())
        .set("visitedPlaces", userDb.getVisitedPlaces());
        final UpdateResults results = getDatastore().update(updateQuery, ops);
        System.out.println(results.getWriteResult());


    }

    public void deleteUser(String id){
        getDatastore().delete(UserEntityMongo.class,new ObjectId(id));
    }
    public User createUser(User user) {
        UserEntityMongo userEntity = UserEntityMongoBuilder.build(user, false);
        userEntity.setDogs(user.getDogs());
        Key<UserEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return UserBuilder.build(userEntity, userEntityMongoKey.getId());

    }

    public List<User> getUsers(){
        List<User> lists = new ArrayList<>();
        for (UserEntityMongo userEntity: getDatastore().find(UserEntityMongo.class)){
            lists.add(UserBuilder.build(userEntity));
        }
        return lists;
    }


}
