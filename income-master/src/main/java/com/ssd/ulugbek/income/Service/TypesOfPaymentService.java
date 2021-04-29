package com.ssd.ulugbek.income.Service;

import com.ssd.ulugbek.income.Model.Reqs.ReqsTypesPayment;

import java.util.List;

public interface TypesOfPaymentService {
    void saveTypesOfPayment(ReqsTypesPayment reqsTypesPayment, String email);

    ReqsTypesPayment putTypesOfPayment(ReqsTypesPayment reqsTypesPayment, String email);

    String deleteTypesOfPayment(String name, String email);

    List<ReqsTypesPayment> getAllTypesOfPayment(String email);
}
