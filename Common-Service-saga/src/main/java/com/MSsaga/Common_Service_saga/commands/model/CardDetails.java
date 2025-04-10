package com.MSsaga.Common_Service_saga.commands.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {
    private  String name;
    private  Integer cardNumber;
    private Integer validUntilMonth;
    private Integer validUntilYear;
    private Integer cvv;
}
