package com.ssd.ulugbek.income.Service.implement;

import com.ssd.ulugbek.income.Model.Entiy.Payment;
import com.ssd.ulugbek.income.Model.Entiy.TypesOfPayment;
import com.ssd.ulugbek.income.Model.Entiy.Users;
import com.ssd.ulugbek.income.Model.Reqs.ReqsPayment;
import com.ssd.ulugbek.income.Repository.PaymentRepos;
import com.ssd.ulugbek.income.Repository.TypesOfPaymentRepos;
import com.ssd.ulugbek.income.Repository.UserRepos;
import com.ssd.ulugbek.income.Service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceimpl implements PaymentService {

    @Autowired
    PaymentRepos paymentRepos;

    @Autowired
    UserRepos userRepos;

    @Autowired
    TypesOfPaymentRepos typesOfPaymentRepos;

    @Override
    public void postPayment(ReqsPayment reqsPayment, String email) {
        SimpleDateFormat format=new SimpleDateFormat();
        Date date=new Date(String.valueOf(format));

        Users users = userRepos.findUsersByEmail(email);
        if (users == null)throw new UsernameNotFoundException(email);

        TypesOfPayment typesOfPayment=typesOfPaymentRepos.getByNameAndUsersId(reqsPayment.getReqsTypesPayment(), users.getId());
        if (typesOfPayment!=null)throw new UsernameNotFoundException(reqsPayment.getReqsTypesPayment());

        Payment payment=new Payment();
        BeanUtils.copyProperties(reqsPayment, payment);

        payment.setUsers(users);
        payment.setDate((java.sql.Date) date);
        payment.setTypesOfPayment(typesOfPayment);
        paymentRepos.save(payment);

        List<Payment> list = users.getPayments();
        list.add(payment);
        users.setPayments(list);

        List<Payment> list1 = typesOfPayment.getPayment();
        list1.add(payment);
        typesOfPayment.setPayment(list1);

        userRepos.save(users);
        typesOfPaymentRepos.save(typesOfPayment);
    }

    @Override
    public String getSummAmount(Date start, Date end) {




        return null;
    }
}
