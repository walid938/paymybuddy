package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApplicationUserServiceImplTest {

    // Mocks pour les dépendances
    @Mock
    private ApplicationuserRepository applicationuserRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // Le service à tester, avec les dépendances injectées
    @InjectMocks
    private ApplicationUserServiceImpl applicationUserService;

    // Méthode de configuration pour initialiser les mocks avant chaque test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Cas de test pour la méthode findAll
    @Test
    void findAll_ShouldReturnListOfUsers() {
        // Arrange
        List<DaoApplicationUser> userList = Arrays.asList(
                new DaoApplicationUser(1, "user1", "user1@example.com", "password1"),
                new DaoApplicationUser(2, "user2", "user2@example.com", "password2")
        );
        when(applicationuserRepository.findAll()).thenReturn(userList);

        // Act
        List<DaoApplicationUser> result = applicationUserService.findAll();

        // Assert
        assertEquals(userList.size(), result.size());
        assertEquals(userList, result);
    }

    // Cas de test pour la méthode findById lorsque l'utilisateur existe
    @Test
    void findById_ShouldReturnUserById() {
        // Arrange
        int userId = 1;
        DaoApplicationUser user = new DaoApplicationUser(userId, "user1", "user1@example.com", "password1");
        when(applicationuserRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        Optional<DaoApplicationUser> result = applicationUserService.findById(userId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    // Cas de test pour la méthode findById lorsque l'utilisateur n'existe pas
    @Test
    void findById_ShouldReturnEmptyOptionalForNonExistingUser() {
        // Arrange
        int userId = 1;
        when(applicationuserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        Optional<DaoApplicationUser> result = applicationUserService.findById(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

    // Cas de test pour la méthode save
    @Test
    void save_ShouldSaveUser() {
        // Arrange
        DaoApplicationUser user = new DaoApplicationUser(1, "user1", "user1@example.com", "password1");
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(applicationuserRepository.save(any(DaoApplicationUser.class))).thenReturn(new DaoApplicationUser(1, "user1", "user1@example.com", "encodedPassword"));

        // Act
        Integer userId = applicationUserService.save(user);

        // Assert
        assertNotNull(applicationuserRepository);
        verify(applicationuserRepository, times(1)).save(any(DaoApplicationUser.class));
    }
}
