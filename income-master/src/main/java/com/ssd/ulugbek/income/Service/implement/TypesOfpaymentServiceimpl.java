package com.ssd.ulugbek.income.Service.implement;

import com.ssd.ulugbek.income.Model.Entiy.TypesOfPayment;
import com.ssd.ulugbek.income.Model.Entiy.Users;
import com.ssd.ulugbek.income.Model.Reqs.ReqsTypesPayment;
import com.ssd.ulugbek.income.Repository.TypesOfPaymentRepos;
import com.ssd.ulugbek.income.Repository.UserRepos;
import com.ssd.ulugbek.income.Service.TypesOfPaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.websocket.server.ServerEndpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypesOfpaymentServiceimpl implements TypesOfPaymentService {

    @Autowired
    TypesOfPaymentRepos typesOfPaymentRepos;

    @Autowired
    UserRepos userRepos;

    @Override
    public void saveTypesOfPayment(ReqsTypesPayment reqsTypesPayment, String email) {
        Users users = userRepos.findUsersByEmail(email);
        if (users == null)throw new UsernameNotFoundException(email);

        TypesOfPayment typesOfPayment=typesOfPaymentRepos.getByNameAndUsersId(reqsTypesPayment.getName(), users.getId());
        if (typesOfPayment!=null)throw new UsernameNotFoundException(reqsTypesPayment.getName());

        typesOfPayment = new TypesOfPayment();
        BeanUtils.copyProperties(reqsTypesPayment, typesOfPayment);
        typesOfPaymentRepos.save(typesOfPayment);
    }

    @Override
    public ReqsTypesPayment putTypesOfPayment(ReqsTypesPayment reqsTypesPayment, String email) {
        Users users = userRepos.findUsersByEmail(email);
        if (users == null)throw new UsernameNotFoundException(email);

        TypesOfPayment typesOfPayment=typesOfPaymentRepos.getByNameAndUsersId(reqsTypesPayment.getName(), users.getId());
        if (typesOfPayment==null)throw new UsernameNotFoundException(reqsTypesPayment.getName());

        BeanUtils.copyProperties(reqsTypesPayment, typesOfPayment);
        typesOfPaymentRepos.save(typesOfPayment);
        BeanUtils.copyProperties(typesOfPayment,reqsTypesPayment);
        return reqsTypesPayment;
    }

    @Override
    public String deleteTypesOfPayment(String name, String email) {
        Users users = userRepos.findUsersByEmail(email);
        if (users == null)throw new UsernameNotFoundException(email);

        TypesOfPayment typesOfPayment=typesOfPaymentRepos.getByNameAndUsersId(name, users.getId());
        if (typesOfPayment!=null)throw new UsernameNotFoundException(name);

        typesOfPaymentRepos.delete(typesOfPayment);
        return name;
    }


//    be yerda map ishlaydimi yaxshi bilmiman shuning uchun list2 bn reqsTypesPayments ni sout qilganman
    @Override
    public List<ReqsTypesPayment> getAllTypesOfPayment(String email) {
        Users users = userRepos.findUsersByEmail(email);
        if (users == null)throw new UsernameNotFoundException(email);
        List<ReqsTypesPayment> reqsTypesPayments=new ArrayList<>();
        List<TypesOfPayment> lists=typesOfPaymentRepos.findAllByid(users.getId());
        List<ReqsTypesPayment> list2 = lists.stream().map(list -> {
            ReqsTypesPayment reqsTypesPayment=new ReqsTypesPayment();
            BeanUtils.copyProperties(list, reqsTypesPayment);
            reqsTypesPayments.add(reqsTypesPayment);
            return reqsTypesPayment;
        }).collect(Collectors.toList());

        System.out.println(list2);
        System.out.println(reqsTypesPayments);

        return reqsTypesPayments;
    }

}
