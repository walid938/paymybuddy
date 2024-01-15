package com.wayl.paymybuddy.controller;

import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.service.ApplicationUserService;
import com.wayl.paymybuddy.service.BankaccountService;
import com.wayl.paymybuddy.service.FriendsService;
import com.wayl.paymybuddy.service.TransactionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping
public class HomeController {

    private ApplicationUserService applicationuserService;
    private BankaccountService bankaccountService;
    private TransactionService transactionService;
    private FriendsService friendsService;

    // Utilisez les services corrects dans le constructeur
    public HomeController(ApplicationUserService applicationuserService,
                          BankaccountService bankaccountService,
                          TransactionService transactionService,
                          FriendsService friendsService) {
        this.applicationuserService = applicationuserService;
        this.bankaccountService = bankaccountService;
        this.transactionService = transactionService;
        this.friendsService = friendsService;
    }

    @GetMapping({"/", "/home"})
    public String getHome(Authentication authentication, Model model) {

        // Le nom a été modifié en tant qu'e-mail :
        DaoApplicationUser user = applicationuserService.findByEmail(authentication.getName());
        String name = user.getUserName();

        // Utilisez les types corrects et les services
        Set<DaoApplicationUser> myFriends = friendsService.findAllMyFriends(user.getId());

        // Utilisez les types corrects et les services
        Bankaccount account = bankaccountService.findByUserId(user.getId());

        // Ajoutez les attributs au modèle
        model.addAttribute("transactionList", (account == null) ? new ArrayList<>() : transactionService.findTransactionsByFromAccount_Id(account.getId()));
        model.addAttribute("friendList", myFriends == null ? new HashSet<>() : myFriends);

        model.addAttribute("message", "Hi " + name);
        model.addAttribute("balance", account == null ? new BigDecimal(0) : account.getBalance());
        return "home";
    }
}
