package org.example.dao;


import org.example.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository
public class ProfileRepository {

    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;

    public boolean saveProfile(UserProfile userProfile) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(userProfile);
            transaction.commit();
        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return false;
    }
}
