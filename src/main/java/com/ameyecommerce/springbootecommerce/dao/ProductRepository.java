package com.ameyecommerce.springbootecommerce.dao;

import com.ameyecommerce.springbootecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {


}
