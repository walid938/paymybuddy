package com.wayl.paymybuddy.controller;

import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.dto.BankaccountDto;
import com.wayl.paymybuddy.model.dto.TransactionDto;
import com.wayl.paymybuddy.service.ApplicationUserService;
import com.wayl.paymybuddy.service.BankaccountService;
import com.wayl.paymybuddy.service.FriendsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/account")
public class BankaccountController {

    private BankaccountService bankaccountService;
    private ApplicationUserService applicationuserService;
    private FriendsService friendsService;

    public BankaccountController(BankaccountService bankaccountService, ApplicationUserService applicationuserService, FriendsService friendsService) {
        this.bankaccountService = bankaccountService;
        this.applicationuserService = applicationuserService;
        this.friendsService = friendsService;
    }

    @PostMapping("/edit")
    public String saveOrUpdateAccount(Authentication authentication,
                                      @ModelAttribute("newAccount") BankaccountDto bankaccountDto,
                                      Model model) {
        boolean success = false;
        int errorType = 1;
        String email = authentication.getName();

        if (bankaccountDto.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            return "error-400"; // Retourne une page d'erreur personnalisée pour un solde négatif
        }

        boolean saved = bankaccountService.saveOrUpdate(bankaccountDto, email);
        if (saved) {
            success = true;
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";
    }

    @PostMapping("/transfer")
    public String sendMoney(Authentication authentication,
                            @ModelAttribute("newTransfer") TransactionDto transactionDto, Model model) {
        boolean isFriend = false;
        boolean success = false;
        int errorType = 0;
        boolean sent;

        // 1. Obtenir l'utilisateur
        String email = authentication.getName();
        DaoApplicationUser fromUser = applicationuserService.findByEmail(email);

        // 2. Obtenir l'ami
        String toEmail = transactionDto.getToEmail();
        DaoApplicationUser toUser = applicationuserService.findByEmail(toEmail);

        // 3. Vérifier si les deux comptes existent
        Bankaccount fromAcc = bankaccountService.findByUserId(fromUser.getId());
        Bankaccount toAcc = bankaccountService.findByUserId(toUser.getId());

        if (toAcc == null) {
            errorType = 4;
        } else if (fromAcc == null) {
            errorType = 1;
        } else {
            // 4. Vérifier si l'ami est dans les connexions
            if (friendsService.isFriend(fromUser.getId(), toUser.getId()) == 1) {
                isFriend = true;
            }

            // 5. Envoyer de l'argent
            if (isFriend) {
                sent = bankaccountService.sendMoney(fromAcc, toAcc, transactionDto.getAmount(), transactionDto.getDescription());
                if (sent) {
                    success = true;
                } else {
                    errorType = 3;
                }
            }
        }

        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        return "result";
    }
}
