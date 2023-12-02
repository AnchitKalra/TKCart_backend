package org.example.dao;


import org.example.model.Cart;
import org.example.model.Order;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    OrderRepository orderRepository;

    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory entityManagerFactory;

    public Cart updateCart(Cart cart) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(cart);
            transaction.commit();
        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return cart;
    }


    public List<Cart> getCart(User userId) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            TypedQuery<Cart> typedQuery = em.createQuery("Select c from Cart c where c.user = :userId", Cart.class);
            typedQuery.setParameter("userId", userId);
             List<Cart> cartList = typedQuery.getResultList();
             List<Cart> cList = new ArrayList<>();
             List<Order> orders = orderRepository.getOrder(userId);
             for(Order o : orders) {
                 List<Cart> cartList1 = o.getCartList();
                 for(Cart cart : cartList1) {

                     for(Cart c: cartList) {

                         if(cart.getId().equals(c.getId())) {

                             cList.add(c);
                             break;
                         }
                     }
                 }
             }

             System.out.println(cList);
             for(Cart c : cList) {
                 try{
                     cartList.remove(c);
                 }catch (Exception e) {
                    System.out.println(e);
                 }
             }
             return cartList;


        }
        catch (Exception e) {
            System.out.println(e);
        }
            return null;
    }

    public boolean clearCart(List<Cart> cart) {

            EntityManager em = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = em.getTransaction();
        try{
            for(Cart c : cart) {
                transaction.begin();
                Integer id = c.getId();
                Query query = em.createQuery("delete  from Cart c where c.id = :id");
                query.setParameter("id", id);
                query.executeUpdate();
                transaction.commit();
            }

            return true;

        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return false;
    }

    public boolean updateQuantity(Integer id, Integer plusMinus) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            TypedQuery<Cart> query = em.createQuery("select c from Cart c where c.id = :id", Cart.class);
            query.setParameter("id", id);
             Cart c = query.getSingleResult();
            if(plusMinus == 1) {
                c.setQuantity(c.getQuantity() + 1);
                c.setTotalValue(c.getTotalValue() + c.getPrice());
                updateCart(c);
            }
            else  {
                if(c.getQuantity() == 1) {
                    List<Cart> cartList = new ArrayList<>();
                    cartList.add(c);
                    clearCart(cartList);
                }
                else {
                    c.setQuantity(c.getQuantity() - 1);
                    c.setTotalValue(c.getTotalValue() - c.getPrice());
                    updateCart(c);
                }
            }

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
