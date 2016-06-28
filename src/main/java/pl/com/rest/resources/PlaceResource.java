package pl.com.rest.resources;


import pl.com.rest.database.PlaceDatabase;
import pl.com.rest.database.UserDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.model.Place;
import pl.com.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

/**
 * Created by wewe on 29.05.16.
 */

@Path("/places")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaceResource {

    private static final PlaceDatabase placeDatabase = new PlaceDatabase();
    private static final UserDatabase userDatabase = new UserDatabase();

    @GET
    public Collection<Place> getPlaces(){
        return placeDatabase.getPlaces();
    }

    @POST
    public Response createPlace(@Valid Place place){
        Place dbPlace = new Place(
                "",
                place.getName(),
                place.getAddress(),
                place.getCity(),
                place.getOtherInformation()

        );
        return Response.ok(placeDatabase.createPlace(dbPlace)).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{placeId}")
    public Place getPlace(@PathParam("placeId") String placeId) throws AppException {
        Place place = placeDatabase.getPlace(placeId);
        if (place == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }
        return place;
    }


    @DELETE
    @Path("/{placeId}")
    public void deletePlace(@PathParam("placeId") String placeId) {
        placeDatabase.deletePlace(placeId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{placeId}/visitors")
    public Collection<User> getVisitors(@PathParam("placeId") String placeId) throws AppException{
        Place place = placeDatabase.getPlace(placeId);
        if (place == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }
        else{
            Collection<User> visitors = userDatabase.getUsers();
            for (User visitor : visitors){
                if (visitor.getVisitedPlaces().contains(place))
                    visitors.add(visitor);
            }

            return visitors;
        }
    }

    @POST
    @Path("/{placeId}/visitors")
    public boolean setVisitors(@PathParam("placeId") String placeId, @QueryParam("userId") String userId) throws AppException{
        Place place = placeDatabase.getPlace(placeId);
        if (place == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }

        User visitor = userDatabase.getUser(userId);
        if (visitor == null){
            throw new AppException(404, 990, "User with id " + userId + " does not exist", null, null);
        }
        visitor.setId(userId);
        if(visitor.getVisitedPlaces().contains(place)) throw new AppException(409, 999, "Visitor with id " + visitor.getId() + "has already this place in his lists", null, null);
        visitor.getVisitedPlaces().add(place);
       //update user
        userDatabase.updateUser(visitor);
        return true;

    }



}
