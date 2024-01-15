package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.repository.TransactionRepository;
import com.wayl.paymybuddy.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Integer save(Transaction transaction) {
        // Enregistre la transaction et retourne son ID
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<Transaction> findAll() {
        // Récupère toutes les transactions
        return transactionRepository.findAll();
    }

    @Override
    public Optional<Transaction> findById(int id) {
        // Récupère une transaction par son ID
        return transactionRepository.findById(id);
    }

    @Override
    public Page<Transaction> findTransactionsByFromAccount_Id(int id, PageRequest pageRequest) {
        // Récupère les transactions par l'ID du compte expéditeur avec une pagination
        return transactionRepository.findTransactionsByFromAccount_Id(id, pageRequest);
    }

    @Override
    public List<Transaction> findTransactionsByFromAccount_Id(int id) {
        // Récupère les transactions par l'ID du compte expéditeur
        return transactionRepository.findTransactionsByFromAccount_Id(id);
    }
}
