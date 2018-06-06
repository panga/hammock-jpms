package hammock.jpms;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/hello")
@RequestScoped
public class HelloResource {

    @GET
    public Response hello() {
        Person person = new Person();
        person.setName("Leonardo");

        return Response.ok(person).build();
    }
}
