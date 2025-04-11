package com.MSPatterns.OrderService.command.api.aggregate.saga;

import com.MSPatterns.OrderService.command.api.events.OrderCreatedEvent;
import com.MSsaga.Common_Service_saga.commands.commands.ValidatePaymentCommand;
import com.MSsaga.Common_Service_saga.commands.model.User;
import com.MSsaga.Common_Service_saga.commands.queries.GetUserPaymentDetailsQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Saga
@Slf4j
public class OrderProcessingSaga {

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    @Autowired
    public OrderProcessingSaga(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent event){
        log.info("OrderCreatedEvent in Saga for Order Id :{} ",
                event.getOrderId());
        GetUserPaymentDetailsQuery getUserPaymentDetailsQuery
                = new GetUserPaymentDetailsQuery(event.getUserId());

        User user = null;
        try {
            user=queryGateway.query(
                    getUserPaymentDetailsQuery,
                    ResponseTypes.instanceOf(User.class)
            ).join();

        }catch (Exception e){
            log.error(e.getMessage());
            //Start the Compensating transaction

        }


        ValidatePaymentCommand validatePaymentCommand
                =ValidatePaymentCommand.builder()
                .cardDetails(user.getCardDetails())
                .orderId(event.getOrderId())
                .paymentId(UUID.randomUUID().toString())
                .build();
        commandGateway.sendAndWait(validatePaymentCommand);


    }
}
