package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import model.Inventory;
import util.EntityManagerProvider;

import java.util.List;

@ApplicationScoped
public class InventoryRepository {
    @Inject
    private EntityManagerProvider entityManagerProvider;

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        EntityManager em = entityManagerProvider.getEntityManager();
        em.persist(inventory);
        return inventory;
    }

    @Transactional
    public Inventory getInventoryById(int id) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.find(Inventory.class, id);
    }

    @Transactional
    public List<Inventory> getInventoriesByFilmId(int filmId) {
        EntityManager em = entityManagerProvider.getEntityManager();
        return em.createQuery("SELECT i FROM Inventory i WHERE i.filmId = :filmId", Inventory.class)
                .setParameter("filmId", filmId)
                .getResultList();
    }
}
