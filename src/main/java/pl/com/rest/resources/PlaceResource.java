package pl.com.rest.resources;


import pl.com.rest.database.PlaceDatabase;
import pl.com.rest.exception.AppException;
import pl.com.rest.model.Place;
import pl.com.rest.model.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;

/**
 * Created by wewe on 29.05.16.
 */

@Path("/places")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlaceResource {

    private static final PlaceDatabase database = new PlaceDatabase();

    @GET
    public Collection<Place> getPlaces(){
        return database.getPlaces();
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
        return Response.ok(database.createPlace(dbPlace)).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{placeId}")
    public Place getPlace(@PathParam("placeId") String placeId) throws AppException {
        Place place = database.getPlace(placeId);
        if (place == null){
            throw new AppException(404, 990, "Place with id " + placeId + " does not exist", null, null);
        }
        return place;
    }


    @DELETE
    @Path("/{placeId}")
    public void deletePlace(@PathParam("placeId") String placeId) {
        database.deletePlace(placeId);
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("/{placeId}/visitors")
//    public List<User> getVisitors(@PathParam("placeId") String placeId){
//
//    }

}
