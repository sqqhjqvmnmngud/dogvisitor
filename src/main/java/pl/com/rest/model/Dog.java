package pl.com.rest.model;

/**
 * Created by wewe on 24.06.16.
 */
public class Dog {
    private String id;

    private String name;
    private String breed;
    private String additionalInformation;
  public Dog(){
  }


    public Dog(String id, String name, String breed, String additionalInformation) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.additionalInformation = additionalInformation;
    }

    public String getId() {
        return id;
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
