package services;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Store;
import repository.StoreRepository;

import java.util.List;

@Path("/stores")
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
    public Store getStoreById(@PathParam("id") int storeId) {
        return storeRepository.getStoreById(storeId);
    }

}
