package org.example.dao;


import org.example.model.Order;
import org.example.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;
public boolean createOrder(Order order) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
    try{
        transaction.begin();
        em.merge(order);
        transaction.commit();
        return true;
    }
    catch (Exception e) {
        System.out.println(e);
        transaction.rollback();
    }
    return false;
}

public List<Order> getOrder(User user) {
    try{
        EntityManager em = emf.createEntityManager();
        TypedQuery<Order> typedQuery = em.createQuery("Select o from Order o where o.user = :userId", Order.class);
        typedQuery.setParameter("userId", user);
        return typedQuery.getResultList();
    }
    catch (Exception e) {
        System.out.println(e);
    }
    return null;
}

}
