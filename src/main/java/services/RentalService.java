package services;

import dtos.RentalDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Rental;
import repository.RentalRepository;
import validators.RentalValidator;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Path("/rentals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RentalService {
    @Inject
    private RentalRepository rentalRepository;
    @Inject
    private RentalValidator rentalValidator;

    @POST
    public Response createRental(@Valid RentalDto rentalDto) {
        try {
            if (rentalValidator.isvalidRental(rentalDto)) {
                rentalRepository.createRental(rentalDto);
                return Response.status(Response.Status.CREATED)
                        .entity("Rental created")
                        .build();
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Invalid Rental data. Only allowed fields: amount (decimal), customer (int), rental (int), staff (int), date (yyyy-MM-dd HH:mm)")
                        .build();
            }
        } catch (ValidationException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid Rental data. " + e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Rental getRentalById(@PathParam("id") int id) {
        return rentalRepository.getRentalById(id);
    }

    @PUT
    @Path("/{id}/returned")
    public Response markRentalReturned(@PathParam("id") int id) {
        Rental rental = rentalRepository.getRentalById(id);
        if (rental == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (rental.getReturnDate() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rental already terminated.")
                    .build();
        }
        Timestamp currentTimestamp = Timestamp.from(Instant.now());
        rental.setReturnDate(currentTimestamp);

        rentalRepository.updateRental(rental);

        return Response.status(Response.Status.OK)
                .entity("Rental is terminated.")
                .build();
    }
}
