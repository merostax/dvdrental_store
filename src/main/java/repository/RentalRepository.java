package repository;

import dtos.RentalDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Inventory;
import model.Rental;
import model.Staff;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RentalRepository {
    @Inject
    InventoryRepository inventoryRepository;
    @Inject
    StaffRepository staffersRepository;
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Rental createRental(RentalDto rentaldto) {
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
        return em.find(Rental.class, id);
    }

    @Transactional
    public Rental updateRental(Rental rental) {
        return em.merge(rental);
    }

    @Transactional
    public List<Rental> getRentalsByFilmId(int filmId) {
        TypedQuery<Rental> query = em.createQuery(
                "SELECT r FROM Rental r WHERE r.inventoryByInventoryId.filmId = :filmId",
                Rental.class
        );
        query.setParameter("filmId", filmId);

        return query.getResultList();
    }
}
