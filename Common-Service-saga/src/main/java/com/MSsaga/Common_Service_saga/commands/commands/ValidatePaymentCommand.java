package com.MSsaga.Common_Service_saga.commands.commands;

import com.MSsaga.Common_Service_saga.commands.model.CardDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ValidatePaymentCommand {
    @TargetAggregateIdentifier
    private  String paymentId;
    private  String orderId;
    private CardDetails cardDetails;

}
