package com.ameyecommerce.springbootecommerce.dto;


import lombok.Data;

@Data
public class PurchaseResponse {
    // needs to be final for creating a constructor with this argument
    private final String orderTrackingNumber;


}
