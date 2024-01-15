package com.wayl.paymybuddy.repository;


import com.wayl.paymybuddy.model.DaoApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface 	ApplicationuserRepository extends JpaRepository<DaoApplicationUser, Integer> {
	// Requête personnalisée pour rechercher un utilisateur par son adresse e-mail
	DaoApplicationUser findByEmail(String email);
	 // Supprimer un utilisateur par son adresse e-mail
    void deleteByEmail(String email);
}
