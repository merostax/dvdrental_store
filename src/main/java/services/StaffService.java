package services;

import java.util.Optional;

import dtos.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Staff;
import repository.StaffRepository;
import util.DTOEntityUtil;
import util.Hrefs;

@Path("staff")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class StaffService {
    @Inject DTOEntityUtil DTOEntityUtil;

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
    public Response getStaffById(@PathParam("id") int id) {
        Optional<Staff> staffOptional = Optional.ofNullable(staffRepository.getStaffById(id));

        if (staffOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Staff not found.")
                    .build();
        }
        Staff staff = staffOptional.get();
        UserDTO userDTO = DTOEntityUtil.createUserDTO(staff);
        return Response.ok(userDTO).build();
    }
}
