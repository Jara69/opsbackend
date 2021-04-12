package cz.educanet.webik;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Path("games")

public class GamesResource {

    @Inject
    private GamesManager gamesManager;
    @Inject
    private LoggedUserManager loggedUserManager;
    @GET
    public Response getAll() {
        if(loggedUserManager.user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(gamesManager.getGamesList()).build();
    }
    @GET
    @Path("{id}")
    public Response getGame(@PathParam("id") int id) {
        if(loggedUserManager.user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok(gamesManager.getGame(id)).build();
    }

    @POST
    public Response createGame(GameDetail gameDetail){
        if(loggedUserManager.user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if(!gamesManager.create(gameDetail))
            return Response.status(400).build();
        return Response.ok(gameDetail).build();
    }

    @PUT
    @Path("{id}")
    public Response editGame(@PathParam("id") int id, GameDetail gameDetail) {
        if(loggedUserManager.user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if(gamesManager.editGame(id, gameDetail)) {
            return Response.ok(gameDetail).build();
        }
        else return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    public Response eraseGame(@PathParam("id") int id) {
        if(loggedUserManager.user == null) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        if(gamesManager.removeGame(id)){
            return Response.ok("Game removed").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
