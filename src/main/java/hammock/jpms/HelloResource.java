package hammock.jpms;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("/hello")
@RequestScoped
public class HelloResource {

    @GET
    public Person hello() {
        Person person = new Person();
        person.setName("Leonardo");
        return person;
    }
}
