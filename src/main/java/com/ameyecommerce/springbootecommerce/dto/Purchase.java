package com.ameyecommerce.springbootecommerce.dto;

import com.ameyecommerce.springbootecommerce.entity.Address;
import com.ameyecommerce.springbootecommerce.entity.Customer;
import com.ameyecommerce.springbootecommerce.entity.Order;
import com.ameyecommerce.springbootecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {


    private Customer customer;

    private Address billingAddress;

    private Address shippingAddress;

    private Order order;

    private Set<OrderItem> orderItems;

}

