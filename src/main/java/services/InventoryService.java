package services;

import java.util.List;
import java.util.Optional;

import dtos.InventoryDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Inventory;
import repository.InventoryRepository;
import util.DTOEntityUtil;

import java.util.Collection;
import java.util.stream.Collectors;

@Path("inventories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InventoryService {
    @Inject
    private InventoryRepository inventoryRepository;

    @GET
    @Path("/film/{filmId}")
    public Response getInventoriesByFilmId(@PathParam("filmId") int filmId) {
        List<Inventory> inventories = inventoryRepository.getInventoriesByFilmId(filmId);
        List<InventoryDTO> inventoryDTOS = inventories.stream()
                .map(this::createInventoryDTO)
                .collect(Collectors.toList());
        return Response.ok(inventoryDTOS).build();
    }

    private InventoryDTO createInventoryDTO(Inventory inventory) {
        return DTOEntityUtil.createInventoryDTO(inventory);
    }

    @GET
    @Path("/{id}")
    public Response getInventoryById(@PathParam("id") int id) {
        Optional<Inventory> inventoryOptional = Optional.ofNullable(inventoryRepository.getInventoryById(id));

        if (inventoryOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Inventory not found.")
                    .build();
        }
        Inventory inventory = inventoryOptional.get();
        InventoryDTO inventoryDTO = this.createInventoryDTO(inventory);
        return Response.ok(inventoryDTO).build();
    }
}
