package pl.com.rest.database;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Key;
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
        return new User(id.toString(), userEntity.getName());

    }
    private User buildUserResponse(UserEntityMongo userEntity){
        return new User(userEntity.getId().toString(), userEntity.getName());

    }

    private UserEntityMongo buildUserEntity(User user, boolean active){
        return new UserEntityMongo(user.getName(),active);
    }

}
