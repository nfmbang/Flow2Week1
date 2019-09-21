package facades;

import entities.Customer;
import entities.ItemType;
import entities.Order;
import entities.OrderLine;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeExample implements FacadeInterface {

    private static FacadeExample instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeExample() {
    }

    /**
     *
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeExample getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeExample();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public Customer createCustomer(String n, String e) {
        EntityManager em = emf.createEntityManager();
        Customer c = new Customer(n, e);
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return c;
    }

    @Override
    public Customer findCustomer(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id);
    }

    @Override
    public List<Customer> getAll() {
        EntityManager em = emf.createEntityManager();
        return em.createNamedQuery("Customer.getAll", Customer.class).getResultList();
    }

    @Override
    public ItemType createItemType(String n, String d, double p) {
        EntityManager em = emf.createEntityManager();
        ItemType it = new ItemType(n, d, p);
        try {

            em.getTransaction().begin();
            em.persist(it);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return it;
    }

    @Override
    public ItemType findItemType(Long id) {
        EntityManager em = emf.createEntityManager();
        return em.find(ItemType.class, id);
    }

    @Override
    public Order createOrder(Customer c, OrderLine ol) {
        EntityManager em = emf.createEntityManager();
        Order o = new Order();
        o.addOrderLine(ol);
        o.setC(c);
        try {
            em.getTransaction().begin();
            em.persist(o);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return o;
    }

    @Override
    public Order createOrderLine(Order o, ItemType it, int qty) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public List<Order> findOrders(Customer c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getTotalPrice(Order o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
