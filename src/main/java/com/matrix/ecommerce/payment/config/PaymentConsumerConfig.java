package com.matrix.ecommerce.payment.config;

import com.matrix.ecommerce.payment.event.OrderEvent;
import com.matrix.ecommerce.payment.event.OrderStatus;
import com.matrix.ecommerce.payment.event.PaymentEvent;
import com.matrix.ecommerce.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class PaymentConsumerConfig {

    @Autowired
    private PaymentService paymentService;

    @Bean
    public Function<Flux<OrderEvent>, Flux<PaymentEvent>> paymentProcessor() {
        return orderEventFlux -> orderEventFlux.flatMap(this::processPayment);
    }

    private Mono<PaymentEvent> processPayment(OrderEvent orderEvent) {
        // get the user id
        // check the balance availability
        // if balance sufficient -> Payment completed and deduct amount from DB
        // if payment not sufficient -> cancel order event and update the amount in DB
        if (OrderStatus.ORDER_CREATED.equals(orderEvent.getOrderStatus())) {
            return Mono.fromSupplier(() -> this.paymentService.newOrderEvent(orderEvent));
        } else {
            return Mono.fromRunnable(() -> this.paymentService.cancelOrderEvent(orderEvent));
        }
    }
}
