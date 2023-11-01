package validators;

import clienTargetRepository.CustomerServiceClientProvider;
import dtos.RentalDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import repository.InventoryRepository;
import repository.StaffRepository;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@ApplicationScoped
public class RentalValidator {
    @Inject
    private CustomerServiceClientProvider customerServiceClientProvider;
    @Inject
    StaffRepository staffRepository;
    @Inject
    InventoryRepository inventoryRepository;
    public boolean isvalidRental(RentalDto rentalDto) {
        return
                isValidCustomer(rentalDto.getCustomer()) &&
                isValidStaff(rentalDto.getStaff()) &&
                isValidPaymentDate(rentalDto.getDate()) &&
                isValidInventory(rentalDto.getInventory());
    }

    private boolean isValidInventory(@Min(1) int inventoryID) {
           return inventoryRepository.getInventoryById(inventoryID)!=null;
    }

    public boolean isValidCustomer(@Min(1) int customerId) {
        Response storeResponse = this.customerServiceClientProvider.getCustomerServiceTarget()
                .path("customers")
                .path(String.valueOf(customerId))
                .request(MediaType.APPLICATION_JSON)
                .get();
        if (storeResponse.getStatus() == Response.Status.OK.getStatusCode()) {
            return true;
        } else {
            throw new NotFoundException("Customer with ID " + customerId + " not found.");
        }
    }

    public boolean isValidStaff(@Min(1) int staffId) {
       return staffRepository.getStaffById(staffId)!=null;
    }

    public boolean isValidPaymentDate(String paymentDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
             new Timestamp(dateFormat.parse(paymentDate).getTime());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}

