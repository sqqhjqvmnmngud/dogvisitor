package pl.com.rest.resources;

import io.swagger.annotations.*;
import pl.com.rest.database.UserDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.model.Dog;
import pl.com.rest.model.Place;
import pl.com.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
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
    @ApiOperation(value = "See all available users",
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
    @ApiOperation(value = "Get one user identified by id",
            response = User.class)
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
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
    @ApiOperation(value = "Edit user")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
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
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public void deleteUser(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        database.deleteUser(userId);
     }

    @GET
    @Path("/{userId}/dogs")
    @ApiOperation(value = "Show all users dogs")
    @ApiResponses( value = {
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Dog> getUsersDog(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Dog> dogsList = dbuser.getDogs();
        return dogsList;

    }

    @POST
    @Path("/{userId}/dogs")
    @ApiOperation(value = "Add new dog to user")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Dog> addUsersDog(@PathParam("userId") String userId,@Valid Dog dog)throws AppException {
        User dbuser = database.getUser(userId);
        if (dbuser == null) {
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Dog> dogsList = new ArrayList<>();
        if(dbuser.getDogs()!=null){
           dogsList = dbuser.getDogs();
        }
        dogsList.add(dog);
        dbuser.setDogs(dogsList);
        database.updateDogUser(dbuser);
        return dogsList;
    }

    @PUT
    @Path("/{userId}/dogs/{dogName}")
    @ApiOperation(value = "Delete dog")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Dog> updateUserDog(@PathParam("userId") String userId, @PathParam("dogName") String dogName,  @Valid Dog dog)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null) {
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }

        if(dbuser.getDogs()==null){
            throw new AppException(404, 990, "User with id " + userId + " don't have any dog" + dogName, null, null);
        }

        if (dbuser.isDogExist(dogName)== -1) throw new AppException(404, 990, "User with id " + userId + " don't have: " + dogName, null, null);
        dbuser.updateDog(dog);
        database.updateDogUser(dbuser);
        return dbuser.getDogs();

    }










    @DELETE
    @Path("/{userId}/dogs/{dogName}")
    @ApiOperation(value = "Delete dog")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Dog> deleteUserDog(@PathParam("userId") String userId, @PathParam("dogName") String dogName)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null) {
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }

        if(dbuser.getDogs()==null){
            throw new AppException(404, 990, "User with id " + userId + " don't have any dog" + dogName, null, null);
        }
        boolean state = dbuser.deleteDog(dogName);
        if (!state)   throw new AppException(404, 990, "User with id " + userId + " don't have: " + dogName, null, null);
        database.updateDogUser(dbuser);
        return dbuser.getDogs();

    }


    @GET
    @Path("/{userId}/visited")
    @ApiOperation(value = "Show all visited places by user")
    public List<Place> getVisitedPlaces(@PathParam("userId") String userId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Place> placeList = dbuser.getVisitedPlaces();
        return placeList;

    }

    @POST
    @Path("/{userId}/visited")
    @ApiOperation(value = "Add new visit place to user")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User not found"),
            @ApiResponse(code = 409, message = "Visited place conflict"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Place> getVisitedPlaces(@PathParam("userId") String userId, Place place)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        List <Place> placeList = new ArrayList<>();
        if(dbuser.getVisitedPlaces()!=null){
            placeList = dbuser.getVisitedPlaces();
            if (dbuser.placeIsVistied(place)){
                throw new AppException(409, 990, "User with id " + userId + "has already visited place with id " + place.getId(), null, null);
            }
        }
        placeList.add(place);
        dbuser.setVisitedPlaces(placeList);
        database.updatePlaceUser(dbuser);
        return placeList;

    }

    @DELETE
    @Path("/{userId}/visited/{placeId}")
    @ApiOperation(value = "Delete place only from user visited places. ")
    @ApiResponses( value = {
            @ApiResponse(code = 404, message = "User or place not found"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    public List<Place> deleteVisitedPlace(@PathParam("userId") String userId, @PathParam("placeId") String placeId)throws AppException{
        User dbuser = database.getUser(userId);
        if (dbuser == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        if(dbuser.getVisitedPlaces()==null){
            throw new AppException(404, 990, "User with id " + userId + " didn't visit any place ", null, null);
        }

        boolean state = dbuser.deleteVisitedPlace(placeId);
        if (!state)   throw new AppException(404, 990, "User with id " + userId + " didn't visit place with id" + placeId, null, null);
        database.updatePlaceUser(dbuser);
        return dbuser.getVisitedPlaces();
    }
}
