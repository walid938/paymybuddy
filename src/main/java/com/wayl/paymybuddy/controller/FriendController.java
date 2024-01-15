package com.wayl.paymybuddy.controller;

import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Friends;
import com.wayl.paymybuddy.model.dto.FriendDto;
import com.wayl.paymybuddy.service.ApplicationUserService;
import com.wayl.paymybuddy.service.FriendsService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/friend")
public class FriendController {

    private ApplicationUserService applicationuserService;
    private FriendsService friendsService;

    public FriendController(ApplicationUserService applicationuserService, FriendsService friendsService) {
        this.applicationuserService = applicationuserService;
        this.friendsService = friendsService;
    }

    @PostMapping
    public String addFriend(Authentication authentication, @ModelAttribute("newFriend") FriendDto friendDto, Model model) {
        // 1. Recherchez l'utilisateur actuel
        String currentUserEmail = authentication.getName();
        DaoApplicationUser currentUser = applicationuserService.findByEmail(currentUserEmail);

        // 2. Vérifiez si l'ami est dans la base de données ou a un compte
        String friendEmail = friendDto.getFriendEmail();
        DaoApplicationUser friendToBe = applicationuserService.findByEmail(friendEmail);

        int errorType = 0;
        boolean success = false;

        if (friendToBe == null || friendToBe.getBankaccount() == null) {
            // L'ami n'existe pas
            errorType = 4;

        } else {
            // Enregistrez l'amitié
            Friends friends = new Friends();
            friends.setUser(currentUser);
            friends.setFriend(friendToBe);
            friendsService.save(friends);
            success = true;
        }
        // Ajoutez des attributs au modèle pour afficher les résultats
        model.addAttribute("success", success);
        model.addAttribute("errorType", errorType);

        // Redirige vers la page de résultat
        return "result";
    }
}
