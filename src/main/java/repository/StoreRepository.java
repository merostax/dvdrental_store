package repository;

import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Store;
import util.EntityManagerProvider;

import java.util.List;

@ApplicationScoped
public class StoreRepository {

    @Inject
    private EntityManagerProvider entityManagerProvider;

        public List<Store> getAllStores() {
            EntityManager em = entityManagerProvider.getEntityManager();
            return em.createQuery("SELECT s FROM Store s", Store.class).getResultList();
        }

        public Store getStoreById(int storeId) {
            EntityManager em = entityManagerProvider.getEntityManager();
          return em.find(Store.class, storeId);
        }


    }




