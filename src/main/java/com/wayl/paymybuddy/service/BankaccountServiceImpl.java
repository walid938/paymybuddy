package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.repository.BankaccountRepository;
import com.wayl.paymybuddy.repository.TransactionRepository;
import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Transaction;
import com.wayl.paymybuddy.model.dto.BankaccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class BankaccountServiceImpl implements BankaccountService {
    private final BankaccountRepository bankaccountRepository;
    private final TransactionRepository transactionRepository;
    private final ApplicationuserRepository applicationuserRepository;

    @Autowired
    public BankaccountServiceImpl(BankaccountRepository bankaccountRepository, TransactionRepository transactionRepository, ApplicationuserRepository applicationuserRepository) {
        this.bankaccountRepository = bankaccountRepository;
        this.transactionRepository = transactionRepository;
        this.applicationuserRepository = applicationuserRepository;
    }



    @Override
    public Bankaccount findByUserId(int id) {
        return bankaccountRepository.findByUserId(id);
    }

   

	@Override
	public Boolean saveOrUpdate(BankaccountDto bankaccountDto, String email) {
		DaoApplicationUser user = applicationuserRepository.findByEmail(email);
        Bankaccount bankaccount = bankaccountRepository.findByUserId(user.getId());
        if (bankaccount == null) {
        	Bankaccount newBankaccount = new Bankaccount();
            newBankaccount.setUser(user);
            newBankaccount.setBalance(bankaccountDto.getBalance());
            bankaccountRepository.save(newBankaccount);
        } else {
        	bankaccountRepository.update(bankaccount.getId(), bankaccountDto.getBalance());
        }
        return true;
	}

	@Override
	public Boolean sendMoney(Bankaccount fromAcc, Bankaccount toAcc, BigDecimal amount, String description) {
		boolean send = false;
        if (fromAcc == null || toAcc == null) {
            return false;
        } else {
            if (fromAcc.getBalance().compareTo(BigDecimal.ONE) > 0
                    && fromAcc.getBalance().compareTo(amount) > 0) {
                // 1, Transfer:
                fromAcc.setBalance(fromAcc.getBalance().subtract(amount.add(new BigDecimal("0.005").multiply(amount))));
                bankaccountRepository.save(fromAcc);
                toAcc.setBalance(toAcc.getBalance().add(amount));
                bankaccountRepository.save(toAcc);

                // 2, Save a record in app:
                Transaction transaction = new Transaction();
                transaction.setFromAccount(fromAcc);
                transaction.setToAccount(toAcc);
                transaction.setDescription(description);
                transaction.setAmount(amount);
                transaction.setTransacted(true);
                transaction.setCharge(new BigDecimal("0.005").multiply(amount));
                transactionRepository.save(transaction);

                // 3, Save a record for user:
                fromAcc.getTransactions().add(transaction);

                send = true;
            }
        }

        return send;
	}

	

}


