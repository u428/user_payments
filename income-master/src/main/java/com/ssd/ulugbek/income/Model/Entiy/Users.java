package com.ssd.ulugbek.income.Model.Entiy;

import com.ssd.ulugbek.income.Model.Entiy.Payment;
import com.ssd.ulugbek.income.Model.Entiy.TypesOfPayment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(name = "user_key")
    private String userKey;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "encrypted_pass")
    private String encreptedPass;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Payment> payments;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<TypesOfPayment> typesOfPayments;



}
