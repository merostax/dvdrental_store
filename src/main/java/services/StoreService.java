package services;

import java.util.Optional;

import dtos.StoreDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Store;
import repository.StoreRepository;
import util.DTOEntityUtil;

@Path("stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StoreService {

    @Inject
    private StoreRepository storeRepository;

    @GET
    @Path("/count")
    public Long getAllStores() {
        return storeRepository.getStoreCount();
    }

    @GET
    @Path("/{id}")
    public Response getStoreById(@PathParam("id") int storeId) {
        Optional<Store> storeOptional = Optional.ofNullable(storeRepository.getStoreById(storeId));

        if (storeOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Store not found.")
                    .build();
        }
        Store store = storeOptional.get();
        StoreDTO storeDTO = DTOEntityUtil.createStoreDTO(store);
        return Response.ok(storeDTO).build();
    }
}
