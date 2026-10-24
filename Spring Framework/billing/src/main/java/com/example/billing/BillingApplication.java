package com.example.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BillingApplication {

    @Bean
    PaymentService paymentService() {
        return new PaymentService();
    }

    @Bean
    OrderService orderService(PaymentService paymentService) {
        return new OrderService(paymentService);
    }

    public static void main(String[] args) {
        try (ConfigurableApplicationContext context =
                SpringApplication.run(BillingApplication.class, args)) {

            OrderService order = context.getBean(OrderService.class);
            order.placeOrder(500);
        }
    }
}