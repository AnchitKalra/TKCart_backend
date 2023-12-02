package org.example.dao;

import org.example.model.Products;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class ProductsRepository {

    @PersistenceUnit(unitName = "tkcart")
    private EntityManagerFactory emf;


    public boolean saveProducts(Products products) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try{
            transaction.begin();
                em.merge(products);
                transaction.commit();
                return true;
        }
        catch (Exception e) {
            System.out.println(e);
            transaction.rollback();
        }
        return false;
    }

    public List<Products> getProducts() {
        try{
            EntityManager em = emf.createEntityManager();
           TypedQuery<Products> typedQuery= em.createQuery("select p from Products p",Products.class);
          return typedQuery.getResultList();

        }catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean deleteProducts(Products products) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Integer id = products.getId();

        try {
            transaction.begin();
            Query query = em.createQuery("delete from Products p where p.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean saveImages(Products products) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(products);
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
