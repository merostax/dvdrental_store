package repository;

import dtos.RentalDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Inventory;
import model.Rental;
import model.Staff;
import util.EntityManagerProvider;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class RentalRepository {
    @Inject
    InventoryRepository inventoryRepository;
    @Inject
    StaffRepository staffersRepository;
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Transactional
    public Rental createRental(RentalDto rentaldto) {
        EntityManager em = entityManagerProvider.getEntityManager();
        Inventory inventory = inventoryRepository.getInventoryById(rentaldto.getInventory());
        Staff staff = staffersRepository.getStaffById(rentaldto.getStaff());

        if (inventory == null || staff == null) {
            return null;
        }
        Rental rental = new Rental();
        rental.setCustomerId(rentaldto.getCustomer());
        rental.setInventoryByInventoryId(inventory);
        rental.setStaffByStaffId(staff);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date parsedDate = dateFormat.parse(rentaldto.getDate());
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            rental.setRentalDate(timestamp);
        } catch (ParseException e) {
            return null;
        }
        em.persist(rental);
        return rental;
    }

    @Transactional
    public Rental getRentalById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Rental.class, id);
    }

    @Transactional
    public Rental updateRental(Rental rental) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.merge(rental);
    }
}
