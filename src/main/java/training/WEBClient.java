package training;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class WEBClient {

    public static void main(String[] args) {
        List <User> users = new ArrayList<>();

        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(getBaseURI());
        GenericType<List<User>> genericType = new GenericType<List<User>>() {
        };
        users = service.path("rest").path("user").accept(MediaType.APPLICATION_JSON).get(genericType);
        for (User temp : users) {
            System.out.println(temp);
        }

        System.out.println("Create user");
        User user = new User("Lex", "Luthor", "lex1", "lex@lexicorp.com");
        service.path("rest").path("user")
                .accept(MediaType.APPLICATION_XML).put(user);

        users = service.path("rest").path("user").accept(MediaType.APPLICATION_JSON).get(genericType);
        for (User temp : users) {
            System.out.println(temp);
        }

        System.out.println("Update user");
        user.setName("udated Lex");
        user.setLastname("updated Luthor");
        service.path("rest").path("user").type(MediaType.APPLICATION_JSON).post(ClientResponse.class, user);
        user = service.path("rest").path("user").path(user.getLogin()).accept(MediaType.APPLICATION_JSON).get(User.class);
        System.out.println("User with login lex1");
        System.out.println(user);

        users = service.path("rest").path("user").accept(MediaType.APPLICATION_JSON).get(genericType);
        for (User temp : users) {
            System.out.println(temp);
        }

        System.out.println("Delete user");
	    service.path("rest").path("user").path(user.getLogin()).delete();

        System.out.println("See users");
        users = service.path("rest").path("user").accept(MediaType.APPLICATION_JSON).get(genericType);
        for (User temp : users) {
            System.out.println(temp);
        }
    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/webServer").build();
    }
}
