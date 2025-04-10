package com.MSPatterns.OrderService.command.api.aggregate.saga;

import com.MSPatterns.OrderService.command.api.events.OrderCreatedEvent;
import com.MSsaga.Common_Service_saga.commands.commands.ValidatePaymentCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;

@Saga
@Slf4j
public class OrderProcessingSaga {

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event){
        log.info("OrderCreatedEvent in Saga for Order Id :{} ",
                event.getOrderId());

        ValidatePaymentCommand validatePaymentCommand = ValidatePaymentCommand.builder().build();
    }
}
