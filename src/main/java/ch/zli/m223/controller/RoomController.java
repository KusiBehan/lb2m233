package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import java.util.List;

import ch.zli.m223.model.Room;
import ch.zli.m223.service.RoomService;

@Path("/room")
@Tag(name = "Rooms", description = "Handling of rooms")
@RolesAllowed({ "Admin" })
public class RoomController {

    @Inject
    RoomService roomService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new room.", description = "Creates a new room and returns thenewly added room.")
    public Room create(Room room) {
        return roomService.createRoom(room);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all Rooms", description = "Get a list of the Rooms")
    public List<Room> getBookings() {
        return roomService.getall();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Equipment löschen", description = "Equipment löschen")
    public Room delete(@PathParam("id") Long buchungId) {
        return roomService.deleteRoom(buchungId);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an user.", description = "Updates an user by its id.")
    public Room update(@PathParam("id") Long id, Room room) {
        return roomService.updateRoom(id, room);
    }
}
