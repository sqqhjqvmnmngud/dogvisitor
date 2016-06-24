package pl.com.rest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import pl.com.rest.database.MongoDB;
import pl.com.rest.database.UserDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

/**
 * Created by wewe on 10.04.16.
 */
@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {



    private static final UserDatabase database = new UserDatabase();


    @GET
    public Collection<User> getUsers(){
        return database.getUsers();
    }


    @POST
    public Response createUser(@Valid User user) {
        User dbUser = new User(
                "",
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );

        return Response.ok(database.createUser(dbUser)).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    public User getUser(@PathParam("userId") String userId) throws AppException {
        User user = database.getUser(userId);
        if (user == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
            return user;
    }
//    @PUT
//    @Path("/{userId}")
//    public User updateUser(@PathParam("userId") String userId, User user){
//        User dbuser = database.getUser(userId);
//        if (dbuser == null){
//            throw new NotFoundException();
//        }
//
//        return user;
//    }

    @DELETE
    @Path("/{userId}")
    public void deleteUser(@PathParam("userId") String userId){
        database.deleteUser(userId);
        //TODO EXCEPTIONS

    }

}
