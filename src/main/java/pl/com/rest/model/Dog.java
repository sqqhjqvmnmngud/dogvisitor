package pl.com.rest.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by wewe on 24.06.16.
 */
@Embedded
public class Dog implements Serializable {

    @NotNull
    @NotEmpty
    private String name;
    private String breed;
    private String additionalInformation;
  public Dog(){
      super();
  }


    public Dog(String name, String breed, String additionalInformation) {
        this.name = name;
        this.breed = breed;
        this.additionalInformation = additionalInformation;
    }



    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }



}
