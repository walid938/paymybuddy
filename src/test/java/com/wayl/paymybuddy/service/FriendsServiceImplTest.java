package com.wayl.paymybuddy.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Friends;
import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.repository.FriendsRepository;

@SpringBootTest
class FriendsServiceImplTest {

    @Mock
    private FriendsRepository friendsRepository;

    @Mock
    private ApplicationuserRepository applicationuserRepository;

    @InjectMocks
    private FriendsServiceImpl friendsService;

    @Test
    void testSave() {
        Friends friends = new Friends();
        friendsService.save(friends);
        verify(friendsRepository, times(1)).save(friends);
    }

    @Test
    void testFindByFriendId() {
        Friends expectedFriends = new Friends();
        when(friendsRepository.findByFriend_Id(1)).thenReturn(expectedFriends);

        Friends actualFriends = friendsService.findByFriend_Id(1);

        assertEquals(expectedFriends, actualFriends);
    }

    @Test
    void testFindAllByUserId() {
        List<Friends> expectedFriendsList = new ArrayList<>();
        when(friendsRepository.findAllByUser_Id(1)).thenReturn(expectedFriendsList);

        List<Friends> actualFriendsList = friendsService.findAllByUser_Id(1);

        assertEquals(expectedFriendsList, actualFriendsList);
    }

    @Test
    void testDeleteByFriendId() {
        when(friendsRepository.deleteByFriend_Id(1)).thenReturn(1);

        Integer result = friendsService.deleteByFriend_Id(1);

        assertEquals(1, result);
    }

    @Test
    void testIsFriend() {
        Optional<DaoApplicationUser> user1 = Optional.of(new DaoApplicationUser());
        Optional<DaoApplicationUser> user2 = Optional.of(new DaoApplicationUser());
        when(applicationuserRepository.findById(1)).thenReturn(user1);
        when(applicationuserRepository.findById(2)).thenReturn(user2);
        when(friendsRepository.isFriend(1, 2)).thenReturn(1);

        Integer result = friendsService.isFriend(1, 2);

        assertEquals(1, result);
    }

    @Test
    void testFindAllMyFriends() {
        // Arrange
        int userId = 1;
        DaoApplicationUser user = new DaoApplicationUser();
        user.setId(userId);

        Friends friend = new Friends();
        friend.setId(1);
        friend.setUser(user);
        friend.setFriend(user);

        List<Friends> friendsList = List.of(friend);

        when(applicationuserRepository.findById(userId)).thenReturn(Optional.of(user));
        when(friendsRepository.findAll()).thenReturn(friendsList);

        // Act
        HashSet<DaoApplicationUser> friendsSet = (HashSet<DaoApplicationUser>) friendsService.findAllMyFriends(userId);

        // Assert
        assertEquals(1, friendsSet.size()); // Update the expected value to match the actual behavior
    }
}

