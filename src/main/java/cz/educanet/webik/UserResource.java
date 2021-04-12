package cz.educanet.webik;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    @Inject
    private LoggedUserManager currentUserManager;

    public static List<User> names = new ArrayList<User>();

    public static boolean userExistsByUsername(String username) {
        for(int i = 0; i < names.size(); i++) {
            if(username.equals(names.get(i).getUsername())) {
                return true;
            }
        }
        return false;
    }

    @POST
    @Path("register")
    public Response createUser(User user) {

        if(userExistsByUsername(user.username)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        names.add(user);
        return Response.ok(user).build();
    }

    @POST
    @Path("login")
    public Response loginUser(User user) {
        for(int x = 0; x < names.size(); x++) {
            User storedUser = names.get(x);
            System.out.println(storedUser.username);
            System.out.println(storedUser.password);
            if (storedUser.username.equals(user.username) && storedUser.password.equals(user.password)) {
                currentUserManager.user = storedUser;
                return Response.ok(storedUser).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getLoggedUser() {
        return  Response.ok(currentUserManager.user).build();
    }

}
