package repository;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import model.Store;
import util.EntityManagerProvider;

import java.util.List;

@ApplicationScoped
public class StoreRepository {

    @Inject
    private EntityManagerProvider entityManagerProvider;
    @Transactional
    public long getStoreCount() {
        EntityManager em = entityManagerProvider.getEntityManager();
        Query query = em.createQuery("SELECT COUNT(s) FROM Store s");
        return (long) query.getSingleResult();
    }
    @Transactional
        public Store getStoreById(int storeId) {
            EntityManager em = entityManagerProvider.getEntityManager();
          return em.find(Store.class, storeId);
        }


    }




