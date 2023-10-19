import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import model.Inventory;
import services.InventoryService;
import services.RentalService;
import services.StaffService;
import services.StoreService;

import java.util.ArrayList;
import java.util.List;

@Path("/")
public class RootResource {

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listEndpoints() {
        List<String> endpoints = new ArrayList<>();

        if (StoreService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = StoreService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }  if (RentalService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = RentalService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }
        if (StaffService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = StaffService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }
        if (InventoryService.class.isAnnotationPresent(Path.class)) {
            Path pathAnnotation = InventoryService.class.getAnnotation(Path.class);
            endpoints.add(uriInfo.getBaseUri() + pathAnnotation.value());
        }

        Jsonb jsonb = JsonbBuilder.create();
        String jsonEndpoints = jsonb.toJson(endpoints);

        return Response.ok(jsonEndpoints).build();
    }
}
