package pl.com.rest.config;

import io.swagger.jaxrs.config.BeanConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.MethodArgumentNotValidException;
import pl.com.rest.exception.*;
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
        register(GenericExceptionMapper.class);
        register(MethodArgumentNotValidException.class);
        register(PoweredByResponseFilter.class);
        register(ConstraintViolationExceptionMapper.class);
        register(MethodArgumentNotValidExceptionMapper.class);

        packages("io.swagger.jaxrs.listing");

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("");
        beanConfig.setResourcePackage("pl.com.rest");
        beanConfig.setScan(true);

        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);


    }
}
