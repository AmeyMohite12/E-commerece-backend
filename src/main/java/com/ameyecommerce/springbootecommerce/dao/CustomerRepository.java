package com.ameyecommerce.springbootecommerce.dao;

import com.ameyecommerce.springbootecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {



}
