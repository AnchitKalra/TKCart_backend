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
public Order createOrder(Order order) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
    try{
        transaction.begin();
        em.merge(order);
        transaction.commit();

    }
    catch (Exception e) {
        System.out.println(e);
        transaction.rollback();
    }

    User user = order.getUser();
    List<Order> orders = getOrder(user);
    Order order1 = null;
    int max = 0;
    for (Order o : orders) {
        if(o.getId() > max) {
            max = o.getId();
            order1 = o;
        }
    }
    return order1;
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

public List<Order> getOrdersList(User user) {
    EntityManager entityManager = emf.createEntityManager();
    try{
      TypedQuery<Order> typedQuery = entityManager.createQuery("Select o from Order o where o.user = :userId", Order.class);
      typedQuery.setParameter("userId", user);
      return typedQuery.getResultList();
    }
    catch (Exception e) {
        System.out.println(e);
    }
    return null;
}

public Order getLastOrder(User user) {
    EntityManager entityManager = emf.createEntityManager();
    try {
        TypedQuery<Order> typedQuery = entityManager.createQuery("select  o   from Order o where o.user = :user", Order.class);
        typedQuery.setParameter("user", user);
        List<Order> orders = typedQuery.getResultList();
        return orders.get(orders.size() - 1);
    }catch (Exception e) {
        System.out.println(e);
    }
    return null;
}


}
