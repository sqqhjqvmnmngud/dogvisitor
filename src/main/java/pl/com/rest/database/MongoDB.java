package pl.com.rest.database;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.Morphia;
import pl.com.rest.entity.PlaceEntityMongo;
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
            morphia.map(UserEntityMongo.class).map(PlaceEntityMongo.class);
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
}
