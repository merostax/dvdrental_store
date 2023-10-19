package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Rental;
import repository.RentalRepository;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RentalService {
    @Inject
    private RentalRepository rentalRepository;

    @POST
    public Response createRental(Rental rental) {
        Rental createdRental = rentalRepository.createRental(rental);
        return Response.status(Response.Status.CREATED)
                .entity(createdRental)
                .build();
    }

    @GET
    @Path("/{id}")
    public Rental getRentalById(@PathParam("id") int id) {
        return rentalRepository.getRentalById(id);
    }

}
