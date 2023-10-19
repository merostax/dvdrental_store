package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Rental;
import util.EntityManagerProvider;

@ApplicationScoped
public class RentalRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Transactional
    public Rental createRental(Rental rental) {
        EntityManager em = entityManagerProvider.getEntityManager();
        em.persist(rental);
        return rental;
    }

    @Transactional
    public Rental getRentalById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Rental.class, id);
    }

}
