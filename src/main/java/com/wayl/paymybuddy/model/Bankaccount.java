package com.wayl.paymybuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bankaccount")
public class Bankaccount extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private DaoApplicationUser user;

    @OneToMany(mappedBy = "fromAccount", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Transaction> transactions = new HashSet<>();

    @Column(name = "balance")
    private BigDecimal balance;
}
