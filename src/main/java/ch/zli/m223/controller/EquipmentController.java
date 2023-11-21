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

import ch.zli.m223.model.Equipment;
import ch.zli.m223.service.EquipmentService;

@Path("/equipment")
@Tag(name = "equipment", description = "Handling of equipment")
@RolesAllowed({ "Admin" })
public class EquipmentController {

    @Inject
    EquipmentService equipmentService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new equipment.", description = "Creates a new equipment")
    public Equipment create(Equipment equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Get all equipments", description = "Get a list of equipments")
    public List<Equipment> getEquipments() {
        return equipmentService.getall();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Equipment löschen", description = "Equipment löschen")
    public Equipment delete(@PathParam("id") Long buchungId) {
        return equipmentService.deleteEquipment(buchungId);
    }

    @Path("/{id}")
    @PUT
    @Operation(summary = "Updates an user.", description = "Updates an user by its id.")
    public Equipment update(@PathParam("id") Long id, Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment);
    }

}
