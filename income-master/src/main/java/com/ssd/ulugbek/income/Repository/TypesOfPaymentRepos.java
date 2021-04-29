package com.ssd.ulugbek.income.Repository;

import com.ssd.ulugbek.income.Model.Entiy.TypesOfPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypesOfPaymentRepos extends JpaRepository<TypesOfPayment, Long> {

    @Query(name = "SELECT * FROM types_of_payment t WHERE t.name=:name AND t.users_id=:id", nativeQuery = true)
    TypesOfPayment getByNameAndUsersId(String name, Long id);

    @Query(name = "SELECT * FROM types_of_payment t WHERE t.users_id=:id", nativeQuery = true)
    List<TypesOfPayment> findAllByid(long id);
}
