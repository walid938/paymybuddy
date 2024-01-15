package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Transaction;
import com.wayl.paymybuddy.model.dto.BankaccountDto;
import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.repository.BankaccountRepository;
import com.wayl.paymybuddy.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class BankaccountServiceImplTest {

    private DaoApplicationUser fromUser = new DaoApplicationUser();
    // Mocks pour les dépendances
    @Mock
    private BankaccountRepository bankaccountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ApplicationuserRepository applicationuserRepository;

    // Classe à tester avec les dépendances injectées (annotations Mockito)
    @InjectMocks
    private BankaccountServiceImpl bankaccountService;

    // Méthode d'initialisation des mocks avant chaque test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Cas de test pour la méthode saveOrUpdate lorsqu'un nouveau compte bancaire doit être créé
    @Test
    void saveOrUpdate_ShouldCreateNewBankAccount() {
        // Arrange
        String userEmail = "test@example.com";
        DaoApplicationUser user = new DaoApplicationUser();
        user.setId(1); // Assurez-vous que l'ID est non nul
        user.setEmail(userEmail);

        BankaccountDto bankaccountDto = new BankaccountDto();
        bankaccountDto.setBalance(new BigDecimal("100.00"));

        Bankaccount existingBankaccount = null;

        when(applicationuserRepository.findByEmail(userEmail)).thenReturn(user);
        when(bankaccountRepository.findByUserId(user.getId())).thenReturn(existingBankaccount);

        // Act
        boolean result = bankaccountService.saveOrUpdate(bankaccountDto, userEmail);

        // Assert
        assertTrue(result);

        // Vous pouvez également ajouter des assertions supplémentaires pour vérifier que le nouveau compte bancaire est créé correctement
    }

    // Cas de test pour la méthode sendMoney lorsque la transaction est effectuée avec succès
    @Test
    void sendMoney_ShouldSendMoneySuccessfully() {
        // Arrange
        Bankaccount fromAccount = new Bankaccount();
        fromAccount.setId(1);
        fromAccount.setBalance(new BigDecimal("500.00"));

        Bankaccount toAccount = new Bankaccount();
        toAccount.setId(2);
        toAccount.setBalance(new BigDecimal("200.00"));

        BigDecimal amount = new BigDecimal("100.00");
        String description = "Paiement";

        when(bankaccountRepository.save(fromAccount)).thenReturn(fromAccount);
        when(bankaccountRepository.save(toAccount)).thenReturn(toAccount);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(new Transaction());

        // Act
        boolean result = bankaccountService.sendMoney(fromAccount, toAccount, amount, description);

        // Assert
        assertTrue(result);
        assertEquals(new BigDecimal("399.50"), fromAccount.getBalance().setScale(2, RoundingMode.HALF_EVEN));
        assertEquals(new BigDecimal("300.00"), toAccount.getBalance().setScale(2, RoundingMode.HALF_EVEN));
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }
    // Ajoutez d'autres cas de test pour les situations particulières et d'autres méthodes...

}

