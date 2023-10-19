package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Staff;
import util.EntityManagerProvider;

@ApplicationScoped
public class StaffRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Transactional
    public Staff createStaff(Staff staff) {
        EntityManager em = entityManagerProvider.getEntityManager();
        em.persist(staff);
        return staff;
    }

    @Transactional
    public Staff getStaffById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Staff.class, id);
    }
}
