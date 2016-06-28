package pl.com.rest.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.mapping.Mapper;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.User;

import java.util.ArrayList;
import java.util.Collection;

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
                return buildUserResponse(userEntity);
            }

            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void updateUser(User user){

        UserEntityMongo userDb = buildUserEntity(user, false);
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
        UserEntityMongo userEntity = buildUserEntity(user,false);
        Key<UserEntityMongo> userEntityMongoKey = getDatastore()
                .save(userEntity);
        return buildUserResponse(userEntity, userEntityMongoKey.getId());

    }

    public Collection<User> getUsers(){
        Collection<User> lists = new ArrayList<>();
        for (UserEntityMongo userEntity: getDatastore().find(UserEntityMongo.class)){
            lists.add(buildUserResponse(userEntity));
        }
        return lists;
    }

    private User buildUserResponse(UserEntityMongo userEntity, Object id){
        return new User(id.toString(), userEntity.getName(), userEntity.getEmail(), userEntity.getPassword());

    }
    private User buildUserResponse(UserEntityMongo userEntity){
        return new User(userEntity.getId().toString(), userEntity.getName(),userEntity.getEmail(), userEntity.getPassword());

    }

    private UserEntityMongo buildUserEntity(User user, boolean active){
        return new UserEntityMongo(user.getName(), user.getEmail(), user.getPassword(), user.getVisitedPlaces(), active);
    }

}
