package pl.com.rest.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.Set;

/**
 * Created by wewe on 29.06.16.
 */
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException ex) {
        ErrorMessage errorMessage = new ErrorMessage();
        setHttpStatus(ex, errorMessage);
        errorMessage.setCode(555);
        Set<ConstraintViolation<?>> set = ex.getConstraintViolations();
        String message ="";

        for(ConstraintViolation<?> c : set) {
            String templ = "";
            templ += c.getMessage() + ": ";
            templ += c.getPropertyPath().toString();
            message += templ + " \n ";

        }
        errorMessage.setMessage(message);

        errorMessage.setDeveloperMessage(null);
        errorMessage.setLink(null);
        return Response.status(errorMessage.getStatus())
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    private void setHttpStatus(ConstraintViolationException ex, ErrorMessage errorMessage) {

        errorMessage.setStatus(Response.Status.BAD_REQUEST.getStatusCode());

    }
}
