package com.ssd.ulugbek.income.Repository;

import com.ssd.ulugbek.income.Model.Entiy.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends JpaRepository<Users, Long> {

    Users findUsersByEmail(String email);
}
