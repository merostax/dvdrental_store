package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Inventory;

import java.util.List;

@ApplicationScoped
public class InventoryRepository {
    @PersistenceContext
    EntityManager em;

    @Transactional
    public Inventory createInventory(Inventory inventory) {
        em.persist(inventory);
        return inventory;
    }

    @Transactional
    public Inventory getInventoryById(int id) {
        return em.find(Inventory.class, id);
    }

    @Transactional
    public List<Inventory> getInventoriesByFilmId(int filmId) {
        return em.createQuery("SELECT i FROM Inventory i WHERE i.filmId = :filmId", Inventory.class)
                .setParameter("filmId", filmId)
                .getResultList();
    }
}
