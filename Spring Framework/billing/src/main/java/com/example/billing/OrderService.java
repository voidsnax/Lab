package com.example.billing;

public class OrderService {
    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void placeOrder(double amount) {
        System.out.println("Order placed.");
        paymentService.pay(amount);
    }
}