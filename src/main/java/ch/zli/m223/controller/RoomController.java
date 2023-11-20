package ch.zli.m223.controller;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
// import org.hibernate.mapping.List;

import java.util.List;

import ch.zli.m223.model.Room;
import ch.zli.m223.service.RoomService;

@Path("/room")
@Tag(name = "buchung", description = "Handling of buchungen")
@RolesAllowed({ "Mitglied", "Admin" })
public class RoomController {

    @Inject
    RoomService roomService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new room.", description = "Creates a new buchung and returns thenewly added buchung.")
    public Room create(Room room) {
        return roomService.createRoom(room);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all your Booking", description = "Get a list of your bookings to check the status")
    public List<Room> getBookings() {
        return roomService.getall();
    }

    // @DELETE
    // @Path("{id}")
    // @Produces(MediaType.APPLICATION_JSON)
    // @Operation(summary = "Buchungen stornieren", description = "Buchungen
    // stornieren")
    // public Buchung delete(@PathParam("id") Long buchungId) {
    // return buchungService.deleteBuchung(buchungId);
    // }

}
