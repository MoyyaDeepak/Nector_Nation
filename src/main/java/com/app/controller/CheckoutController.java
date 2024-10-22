package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final RazorpayClient razorpayClient;

    @Autowired
    public CheckoutController() throws Exception {
        this.razorpayClient = new RazorpayClient("rzp_test_hr66RzeUcyoNO7", "CDgUnHIJQKNwEphU1Narg07n");
    }

    @PostMapping("/create-order")
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            JSONObject options = new JSONObject();
            options.put("amount", orderRequest.getAmount() * 100); // Convert to paise
            options.put("currency", "INR");
            options.put("receipt", "receipt_order_" + Math.random());

            Order order = razorpayClient.orders.create(options);
            return order.toString();
        } catch (Exception e) {
            return "Error creating order: " + e.getMessage();
        }
    }
}

class OrderRequest {
    private double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
