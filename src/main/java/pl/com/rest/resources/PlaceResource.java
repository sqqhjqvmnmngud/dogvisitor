package pl.com.rest.resources;


import io.swagger.annotations.Api;
import pl.com.rest.database.PlaceDatabase;
import pl.com.rest.database.UserDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.model.Place;
import pl.com.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wewe on 29.05.16.
 */

@Path("/places")
@Api(value = "places")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaceResource {

    private static final PlaceDatabase placeDatabase = new PlaceDatabase();
    private static final UserDatabase userDatabase = new UserDatabase();

    @GET
    public List<Place> getPlaces(){
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

    @PUT
    @Path("/{placeId}")
    public Place updatePlace(@PathParam("placeId") String placeId, Place place)  throws AppException {
        Place dbPlace = placeDatabase.getPlace(placeId);
        if (dbPlace == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }
        place.setId(dbPlace.getId());
        placeDatabase.updatePlace(place);
        return place;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{placeId}/visitors")
    public List<User> getVisitors(@PathParam("placeId") String placeId) throws AppException{
        Place place = placeDatabase.getPlace(placeId);
        if (place == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }
        else{
            List<User> tempVisitors = userDatabase.getUsers();
            List<User> visitors = new ArrayList<>();
            for (User visitor : tempVisitors){
                if (visitor.placeIsVistied(place)){

                    visitors.add(visitor);
                }
            }

            return visitors;
        }
    }




}
