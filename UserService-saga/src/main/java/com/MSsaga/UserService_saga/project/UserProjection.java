package com.MSsaga.UserService_saga.project;

import com.MSsaga.Common_Service_saga.commands.model.CardDetails;
import com.MSsaga.Common_Service_saga.commands.model.User;
import com.MSsaga.Common_Service_saga.commands.queries.GetUserPaymentDetailsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class UserProjection {

    @QueryHandler
    public User getUserPaymentDetails(GetUserPaymentDetailsQuery query){
      // Ideally fetch the datils from tyhe DB
        CardDetails cardDetails= CardDetails.builder()
                .name("Naveen")
                .validUntilYear(2026)
                .validUntilMonth(12)
                .cardNumber(Integer.valueOf("1234567898765432"))
                .cvv(609)
                .build();
        return  User.builder()
                .userId(query.getUserId())
                .firstName("Ediga")
                .lastName("Kumar")
                .cardDetails(cardDetails)
                .build();
    }
}
