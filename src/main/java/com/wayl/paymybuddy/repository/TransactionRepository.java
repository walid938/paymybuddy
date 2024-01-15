package com.wayl.paymybuddy.repository;

import com.wayl.paymybuddy.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
	// Requête personnalisée pour rechercher toutes les transactions à partir d'un compte par son ID
	List<Transaction> findTransactionsByFromAccount_Id(int id);
	//Requête personnalisée pour rechercher toutes les transactions à partir d'un compte par son ID avec pagination
    Page<Transaction> findTransactionsByFromAccount_Id(int id, Pageable pageable);
}