package pl.com.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.com.rest.exception.AppExceptionMapper;
import pl.com.rest.exception.NotFoundExceptionMapper;
import pl.com.rest.resources.PlaceResource;
import pl.com.rest.resources.UserResource;

/**
 * Created by wewe on 10.04.16.
 */
@Configuration
public class JerseyConfig extends ResourceConfig{
    public JerseyConfig(){
        register(UserResource.class);
        register(PlaceResource.class);
        register(NotFoundExceptionMapper.class);
        register(AppExceptionMapper.class);
        //register(GenericExceptionMapper.class);
        register(NotFoundExceptionMapper.class);
        register(MethodArgumentNotValidException.class);
       // register(JsonMappingExceptionMapper.class);
       // register(ConstraintViolationExceptionMapper.class);
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);


    }
}
