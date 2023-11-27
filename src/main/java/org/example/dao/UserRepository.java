package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepository {


    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;

    public boolean signupser(User newUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            if(findUser(newUser) == null) {
            transaction.begin();
            em.persist(newUser);
            transaction.commit();
            return true;
            }
        }catch(Exception e) {
            transaction.rollback();
        }
        return false;
    }

    public User login(User loginUser) {
       if(findUserAndPassword(loginUser)) {
           return loginUser;
       }
       return null;

    }

    public User findUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            String username = user.getUsername();
            TypedQuery<User> typedQuery = em.createQuery("Select u from User u where u.username = :username", User.class);
            typedQuery.setParameter("username", username);
             User fetchedUser = typedQuery.getSingleResult();
             //System.out.println(fetchedUser.getName());
             return fetchedUser;

        }
        catch (Exception e) {
            System.out.println("FROM FINDUSER");
            System.out.println(e);
        }
        return null;
    }

    public boolean findUserAndPassword(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            TypedQuery<User> typedQuery = em.createQuery("Select u from User u where u.username = :username and u.password = :password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);
             typedQuery.getSingleResult();
              return true;

        }
        catch (Exception e) {
            System.out.println("FROM LOGIN");
            System.out.println(e);
            return false;
        }
    }
}

