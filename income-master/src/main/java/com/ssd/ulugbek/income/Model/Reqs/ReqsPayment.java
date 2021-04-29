package com.ssd.ulugbek.income.Model.Reqs;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ReqsPayment {

    private long amount;

    private String description;

    private boolean income;

    private String reqsTypesPayment;
}
