package org.example.dao;


import org.example.model.User;
import org.example.model.UserProfile;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class ProfileRepository {

    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;

    public boolean saveProfile(UserProfile userProfile) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
          UserProfile up = getUserProfile(userProfile.getUser());
          deleteProfile(up);
        }catch (Exception e) {
            System.out.println("FROM SAVE PROFILE");
            System.out.println(e);
        }
        try{
            transaction.begin();
            em.persist(userProfile);
            transaction.commit();
        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return false;
    }

    public UserProfile getUserProfile(User user) {
        EntityManager entityManager = emf.createEntityManager();
        try{
           TypedQuery<UserProfile> typedQuery= entityManager.createQuery("select u from UserProfile u where u.user = :user", UserProfile.class);
           typedQuery.setParameter("user", user);
           return typedQuery.getSingleResult();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public boolean deleteProfile(UserProfile userProfile) {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        Integer id = userProfile.getProfileId();
        try{
            transaction.begin();
           Query typedQuery= entityManager.createQuery("delete from UserProfile u where u.profileId = :id");
           typedQuery.setParameter("id", id);
           typedQuery.executeUpdate();
           transaction.commit();
           return true;
        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return false;

    }
}
