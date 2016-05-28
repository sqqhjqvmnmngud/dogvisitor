package pl.com.rest.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import pl.com.rest.entity.UserEntityMongo;
import pl.com.rest.model.User;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;

import static pl.com.rest.database.DatabaseConstant.*;

/**
 * Created by wewe on 28.05.16.
 */
public class MongoDB{



    private static Datastore datastore;

    public static Datastore getDatastore() {
        if (datastore == null) {
            Morphia morphia = new Morphia();
            morphia.map(UserEntityMongo.class);
            MongoClient client = createMongoClient();
            datastore = morphia.createDatastore(client, DATABASE);
        }

        return datastore;
    }

    private static MongoClient createMongoClient() {
        MongoClientURI uri =
                new MongoClientURI(
                        String.format(
                                "mongodb://%s:%s@%s:%s/%s",
                                USER_NAME,
                                PASSWORD,
                                HOST,
                                PORT,
                                DATABASE
                        )
                );

        try {
            return new MongoClient(uri);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

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
