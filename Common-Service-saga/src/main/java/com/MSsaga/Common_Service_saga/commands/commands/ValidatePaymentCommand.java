package com.MSsaga.Common_Service_saga.commands.commands;

import com.MSsaga.Common_Service_saga.commands.model.CardDetails;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidatePaymentCommand {
    private  String paymentId;
    private  String orderId;
    private CardDetails cardDetails;

}
