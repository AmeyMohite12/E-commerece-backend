package com.ameyecommerce.springbootecommerce.service;

import com.ameyecommerce.springbootecommerce.dao.CustomerRepository;
import com.ameyecommerce.springbootecommerce.dto.Purchase;
import com.ameyecommerce.springbootecommerce.dto.PurchaseResponse;
import com.ameyecommerce.springbootecommerce.entity.Customer;
import com.ameyecommerce.springbootecommerce.entity.Order;
import com.ameyecommerce.springbootecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImp implements  CheckoutService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        //retrieve info from DTO
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item->order.add(item));

        // populate order with billingAddress and ShippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to database
        customerRepository.save(customer);
        // return a response

        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // generate a random UUID
        return UUID.randomUUID().toString();
    }
}
