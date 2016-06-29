package pl.com.rest.resources;

import io.swagger.annotations.*;
import pl.com.rest.database.UserDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.exception.MethodArgumentNotValidExceptionMapper;
import pl.com.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by wewe on 10.04.16.
 */
@Path("/users")
@Api(value = "/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {


    private static final UserDatabase database = new UserDatabase();

    @GET
    @ApiOperation(value = "See all available users", notes = "",
            response = User.class,
            responseContainer = "List")
    public List<User> getUsers(){
        return database.getUsers();
    }


    @POST
    @ApiOperation(value = "Add new user", notes = "You can provide dog and existing visited places",
            response = User.class)
    public User createUser(@Valid User user)  {
        User dbUser = new User(
                "",
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getVisitedPlaces(),
                user.getDogs()
        );
        return database.createUser(dbUser);


    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userId}")
    @ApiOperation(value = "Get one user identified by id", notes = " ",
            response = User.class)
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found")
    })
    public User getUser(@PathParam("userId") String userId) throws AppException {
        User user = database.getUser(userId);
        if (user == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
            return user;
    }
    @PUT
    @Path("/{userId}")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found")
    })
    public User updateUser(
            @ApiParam (value = "Id of user to fetch", required = true)@PathParam("userId") String userId, User user) throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        user.setId(dbuser.getId());
        database.updateUser(user);
        return user;
    }

    @DELETE
    @Path("/{userId}")
    @ApiOperation(value = "Delete user identified by id")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found")
    })
    public void deleteUser(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        database.deleteUser(userId);
     }

}
