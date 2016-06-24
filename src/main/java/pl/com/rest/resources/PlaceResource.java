package pl.com.rest.resources;


import pl.com.rest.database.PlaceDatabase;
import pl.com.rest.model.Place;

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

    private static final PlaceDatabase database = new PlaceDatabase();

    @GET
    public Collection<Place> getPlaces(){
        return database.getPlaces();
    }

    @POST
    public Response createPlace(Place place){
        Place dbPlace = new Place(
                "",
                place.getName(),
                place.getAddress(),
                place.getCity(),
                place.getOtherInformation()

        );
        return Response.ok(database.createPlace(dbPlace)).build();
    }


}
