package pl.com.rest.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
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
    }
}
