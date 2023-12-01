package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Staff;

@ApplicationScoped
public class StaffRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Staff createStaff(Staff staff) {
        em.persist(staff);
        return staff;
    }

    @Transactional
    public Staff getStaffById(int id) {
        return em.find(Staff.class, id);
    }
}
