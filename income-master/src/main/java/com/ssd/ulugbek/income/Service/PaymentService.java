package com.ssd.ulugbek.income.Service;

import com.ssd.ulugbek.income.Model.Reqs.ReqsPayment;

import java.util.Date;

public interface PaymentService {
    void postPayment(ReqsPayment reqsPayment, String email);

    String getSummAmount(Date start, Date end);
}
