package services;

import java.util.List;
import java.util.Optional;

import dtos.RentalDto;
import dtos.RentalDtoGet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import model.Rental;
import repository.RentalRepository;
import util.DTOEntityUtil;
import util.Hrefs;
import validators.RentalValidator;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Path("rentals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RentalService {
    @Inject
    private RentalRepository rentalRepository;
    @Inject
    private RentalValidator rentalValidator;
    @Context
    private UriInfo uriInfo;
    @POST
    public Response createRental(@Valid RentalDto rentalDto) {

        try {
            if (rentalValidator.isvalidRental(rentalDto)) {
              Rental rental=  rentalRepository.createRental(rentalDto);
                return Response.status(Response.Status.CREATED)
                        .entity("Rental created")
                        .header("location", Hrefs.STORE.getHref()+"rentals/"+rental.getRentalId())
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
    public Response getRentalById(@PathParam("id") int id) {
        Optional<Rental> rentalOptional = Optional.ofNullable(rentalRepository.getRentalById(id));

        if (rentalOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rental not found.")
                    .build();
        }
        Rental rental = rentalOptional.get();
        RentalDtoGet rentalDto = DTOEntityUtil.createRentalDtoGET(rental);
        return Response.ok(rentalDto).build();
    }


    @PUT
    @Path("/{id}/returned")
    public Response markRentalReturned(@PathParam("id") int id) {
        Optional<Rental> rentalOptional = Optional.ofNullable(rentalRepository.getRentalById(id));

        if (rentalOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Rental not found.")
                    .build();
        }

        Rental rental = rentalOptional.get();

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

    @GET
    @Path("film/{id}/check")
    public Response checkRentalStatus(@PathParam("id") int id) {
        List<Rental> rentals = rentalRepository.getRentalsByFilmId(id);
        if (!rentals.isEmpty()) {
            return Response.status(Response.Status.FORBIDDEN)
                    .entity("Film has associated rentals and cannot be deleted")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity("No associated rentals found. Film can be deleted.")
                .build();
    }
}
