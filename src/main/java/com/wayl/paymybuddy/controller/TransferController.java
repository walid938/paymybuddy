package com.wayl.paymybuddy.controller;

import com.wayl.paymybuddy.model.Bankaccount;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Transaction;
import com.wayl.paymybuddy.service.ApplicationUserService;
import com.wayl.paymybuddy.service.BankaccountService;
import com.wayl.paymybuddy.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ApplicationUserService applicationuserService;

    @Autowired
    private BankaccountService bankaccountService;

    // Affiche la page de transfert avec la liste des transactions
    @GetMapping
    public String getTransfer(Authentication authentication,
                              Model model, @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size) {

        // 1. Récupérer l'utilisateur actuellement authentifié
        DaoApplicationUser user = applicationuserService.findByEmail(authentication.getName());

        // 2. Paramètres de pagination
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        // 3. Configuration du tri par date de création décroissante
        Sort sort = Sort.by(Sort.Order.desc("createdAt"));

        // 4. Récupérer le compte bancaire de l'utilisateur
        Bankaccount bankaccount = bankaccountService.findByUserId(user.getId());

        // 5. Vérifier si le compte bancaire existe
        if (bankaccount != null) {
            // 6. Récupérer la page de transactions
            Page<Transaction> transactionPage = transactionService.findTransactionsByFromAccount_Id(bankaccount.getId(), PageRequest.of(currentPage - 1, pageSize, sort));
            if (transactionPage != null) {
            // 7. Calculer le nombre total de pages pour la pagination
            int totalPages = transactionPage.getTotalPages();

            // 8. Ajouter les numéros de page au modèle
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }
        }
        // 9. Ajouter la liste des transactions au modèle
        model.addAttribute("transactionList", (bankaccount == null) ? new ArrayList<>() : transactionService.findTransactionsByFromAccount_Id(bankaccount.getId(), PageRequest.of(currentPage - 1, pageSize, sort)));

        // 10. Retourner la vue "transfer"
        return "transfer";
    }
}
