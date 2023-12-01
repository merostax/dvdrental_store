package repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import model.Store;

@ApplicationScoped
public class StoreRepository {

    @PersistenceContext
    EntityManager em;
    @Transactional
    public long getStoreCount() {
        Query query = em.createQuery("SELECT COUNT(s) FROM Store s");
        return (long) query.getSingleResult();
    }
    @Transactional
        public Store getStoreById(int storeId) {
          return em.find(Store.class, storeId);
        }


    }




