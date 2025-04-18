package com.MSPatterns.OrderService.command.api.events;

import com.MSPatterns.OrderService.command.api.data.Order;
import com.MSPatterns.OrderService.command.api.data.OrderRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {


    private OrderRepository orderRepository;

    @EventHandler
    public  void on(OrderCreatedEvent event){
    Order order= new Order();
        BeanUtils.copyProperties(event,order);
        orderRepository.save(order);
    }


}
