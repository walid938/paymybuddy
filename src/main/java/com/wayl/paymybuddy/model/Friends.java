package com.wayl.paymybuddy.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "friends")
public class Friends extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DaoApplicationUser user;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    private DaoApplicationUser friend;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != Hibernate.getClass(o)) return false;

        Friends friends = (Friends) o;
        return Objects.equals(getId(), friends.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }



}
