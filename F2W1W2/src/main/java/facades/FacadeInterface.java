/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Customer;
import entities.ItemType;
import entities.Order;
import entities.OrderLine;
import java.util.List;

/**
 *
 * @author nille
 */
public interface FacadeInterface {

    public Customer createCustomer(String n, String e);

    public Customer findCustomer(Long id);

    public List<Customer> getAll();

    public ItemType createItemType(String n, String d, double p);

    public ItemType findItemType(Long id);

    public Order createOrder(Customer c, OrderLine ol);

    public Order createOrderLine(Order o, ItemType it, int qty);

    public List<Order> findOrders(Customer c);

    public double getTotalPrice(Order o);
}
