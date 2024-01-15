package com.wayl.paymybuddy.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.wayl.paymybuddy.repository.TransactionRepository;
import com.wayl.paymybuddy.model.Transaction;
import com.wayl.paymybuddy.service.TransactionServiceImpl;

class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        // Arrange
        Transaction transaction = new Transaction();
        transaction.setId(1);

        when(transactionRepository.save(transaction)).thenReturn(transaction);

        // Act
        Integer savedTransactionId = transactionService.save(transaction);

        // Assert
        assertEquals(transaction.getId(), savedTransactionId);
    }

    @Test
    void testFindAll() {
        // Arrange
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        // Act
        List<Transaction> transactions = transactionService.findAll();

        // Assert
        assertEquals(2, transactions.size());
    }

    @Test
    void testFindById() {
        // Arrange
        int transactionId = 1;
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));

        // Act
        Optional<Transaction> foundTransaction = transactionService.findById(transactionId);

        // Assert
        assertEquals(transaction, foundTransaction.orElse(null));
    }

    @Test
    void testFindTransactionsByFromAccount_Id() {
        // Arrange
        int accountId = 1;
        PageRequest pageRequest = PageRequest.of(0, 10);
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();

        when(transactionRepository.findTransactionsByFromAccount_Id(accountId, pageRequest))
                .thenReturn(new PageImpl<>(Arrays.asList(transaction1, transaction2)));

        // Act
        Page<Transaction> transactionsPage = transactionService.findTransactionsByFromAccount_Id(accountId, pageRequest);

        // Assert
        assertEquals(2, transactionsPage.getContent().size());
    }

    @Test
    void testFindTransactionsByFromAccount_IdWithoutPageRequest() {
        // Arrange
        int accountId = 1;
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();

        when(transactionRepository.findTransactionsByFromAccount_Id(accountId)).thenReturn(Arrays.asList(transaction1, transaction2));

        // Act
        List<Transaction> transactions = transactionService.findTransactionsByFromAccount_Id(accountId);

        // Assert
        assertEquals(2, transactions.size());
    }
}

