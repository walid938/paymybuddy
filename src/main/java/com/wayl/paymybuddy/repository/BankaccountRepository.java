package com.wayl.paymybuddy.repository;

import com.wayl.paymybuddy.model.Bankaccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface BankaccountRepository extends JpaRepository<Bankaccount, Integer> {
    
	// Requête personnalisée pour rechercher un compte bancaire par ID d'utilisateu
	@Query("select a from Bankaccount  a where a.user.id = ?1")
    Bankaccount findByUserId(int id);

	// Transactionnel et modifiant : mise à jour du solde d'un compte bancaire par ID
    @Transactional
    @Modifying
    @Query("update Bankaccount a set a.balance = ?2 where a.id = ?1")
    void update(Integer id, BigDecimal balance);
}
