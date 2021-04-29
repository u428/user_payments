package com.ssd.ulugbek.income.Model.Entiy;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "types_of_payment")
public class TypesOfPayment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "date")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "types_and_payment",
            joinColumns        = { @JoinColumn(name = "types_of_payment_id",       referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "payment_id", referencedColumnName = "id") }
    )
    private List<Payment> payment;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
