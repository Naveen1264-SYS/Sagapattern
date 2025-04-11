package com.MSsaga.Payment_Service_saga.command.api.aggregate;

import com.MSsaga.Common_Service_saga.commands.commands.ValidatePaymentCommand;
import com.MSsaga.Common_Service_saga.events.PaymentProcessedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class PaymentAggregate {

    @AggregateIdentifier
    private  String paymentId;
    private  String orderId;
    private  String paymentStatus;

    public PaymentAggregate() {
    }
    @CommandHandler
    public PaymentAggregate(ValidatePaymentCommand validatePaymentCommand) {
        // Validate  the payment details
        //Publish the Payment Processed event
        log.info("Execuing ValidatePaymentCommand for"  +
                "Order Id : {} and Payment Id: {}",
                validatePaymentCommand.getPaymentId(),
                validatePaymentCommand.getPaymentId());

        PaymentProcessedEvent paymentProcessedEvent= new PaymentProcessedEvent(
                validatePaymentCommand.getPaymentId(), validatePaymentCommand.getOrderId()
        );
        AggregateLifecycle.apply(paymentProcessedEvent);
        log.info("PaymentProcessesEvent Applied ");


    }
}