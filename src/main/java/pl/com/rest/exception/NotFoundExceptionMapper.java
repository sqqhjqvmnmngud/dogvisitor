package pl.com.rest.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 * Created by wewe on 24.06.16.
 */
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    public Response toResponse(NotFoundException ex) {
        return Response.status(ex.getResponse().getStatus())
                .entity(new ErrorMessage(ex))
                .type(MediaType.APPLICATION_JSON) //this has to be set to get the generated JSON
                .build();
    }

}
