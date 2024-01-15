package com.wayl.paymybuddy.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Bankaccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Bankaccount toAccount;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "charge")
    private BigDecimal charge;

    @Column(name = "description")
    private String description;

    @Type(type = "yes_no")
    private Boolean transacted;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

}
