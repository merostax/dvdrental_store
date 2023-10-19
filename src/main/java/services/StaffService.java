package services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Staff;
import repository.StaffRepository;

@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StaffService {
    @Inject
    private StaffRepository staffRepository;

    @POST
    public Response createStaff(Staff staff) {
        Staff createdStaff = staffRepository.createStaff(staff);
        return Response.status(Response.Status.CREATED)
                .entity(createdStaff)
                .build();
    }

    @GET
    @Path("/{id}")
    public Staff getStaffById(@PathParam("id") int id) {
        return staffRepository.getStaffById(id);
    }
}
