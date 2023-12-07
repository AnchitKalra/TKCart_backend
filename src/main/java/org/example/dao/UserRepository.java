package org.example.dao;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepository {


    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;

    public User signupser(User newUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            User user = findUser(newUser);
            if(user != null) {
                if(user.getPassword().equals("Bangles@001"))
                    return user;
            }
            else {
                transaction.begin();
                em.persist(newUser);
                transaction.commit();
                return newUser;
            }
        }catch(Exception e) {
            transaction.rollback();
        }
        return null;
    }

    public User login(User loginUser) {
       User user = findUserAndPassword(loginUser);
          return user;
    }

    public User loginWithToken(String accessToken) {
        EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<User> query= em.createQuery("Select u from User u where u.accessToken = :accessToken", User.class);
            query.setParameter("accessToken", accessToken);
            User user =  query.getSingleResult();
            System.out.println("FROM LOGIN WITH TOKEN REPOSITORY");
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            return user;
        }
        catch (Exception e) {
            System.out.println(e);
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

    public User findUserAndPassword(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            TypedQuery<User> typedQuery = em.createQuery("Select u from User u where u.username = :username and u.password = :password", User.class);
            typedQuery.setParameter("username", username);
            typedQuery.setParameter("password", password);
             return typedQuery.getSingleResult();


        }
        catch (Exception e) {
            System.out.println("FROM LOGIN");
            System.out.println(e);
        }
        return null;
    }

    public Boolean saveUserWithToken(User user) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(user);
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

