package com.MSsaga.Common_Service_saga.commands.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String  firstName;
    private  String lastName;
    private CardDetails cardDetails;

}
