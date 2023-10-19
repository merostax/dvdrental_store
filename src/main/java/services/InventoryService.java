package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Inventory;
import repository.InventoryRepository;

import java.util.Collection;

@Path("/inventories")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class InventoryService {
    @Inject
    private InventoryRepository inventoryRepository;

    @GET
    @Path("/film/{filmId}")
    public Collection<Inventory> getInventoriesByFilmId(@PathParam("filmId") int filmId) {
        return inventoryRepository.getInventoriesByFilmId(filmId);
    }

    @GET
    @Path("/{id}")
    public Inventory getInventoryById(@PathParam("id") int id) {
        return inventoryRepository.getInventoryById(id);
    }
}
