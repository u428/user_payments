package com.ssd.ulugbek.income.Repository;

import com.ssd.ulugbek.income.Model.Entiy.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepos extends JpaRepository<Payment, Long> {

}
