package com.ameyecommerce.springbootecommerce.service;

import com.ameyecommerce.springbootecommerce.dto.Purchase;
import com.ameyecommerce.springbootecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
