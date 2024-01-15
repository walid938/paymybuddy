package com.wayl.paymybuddy.service;

import com.wayl.paymybuddy.repository.FriendsRepository;
import com.wayl.paymybuddy.repository.ApplicationuserRepository;
import com.wayl.paymybuddy.model.DaoApplicationUser;
import com.wayl.paymybuddy.model.Friends;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository friendsRepository;
    private final ApplicationuserRepository applicationuserRepository;

    @Autowired
    public FriendsServiceImpl(FriendsRepository friendsRepository, ApplicationuserRepository applicationuserRepository) {
        this.friendsRepository = friendsRepository;
        this.applicationuserRepository = applicationuserRepository;
    }

    @Override
    public void save(Friends friends) {
        friendsRepository.save(friends);
    }

    @Override
    public Friends findByFriend_Id(int id) {
        return friendsRepository.findByFriend_Id(id);
    }

    @Override
    public List<Friends> findAllByUser_Id(int id) {
        return friendsRepository.findAllByUser_Id(id);
    }

    @Override
    public Integer deleteByFriend_Id(int id) {
        return friendsRepository.deleteByFriend_Id(id);
    }

    @Override
    public Integer isFriend(int id1, int id2) {
        Integer friend = 0;
        Optional<DaoApplicationUser> user1 = applicationuserRepository.findById(id1);
        Optional<DaoApplicationUser> user2 = applicationuserRepository.findById(id2);
        if (user1.isPresent() && user2.isPresent()) {
            friend = friendsRepository.isFriend(id1, id2);

        }
        return friend;
    }

    /**
     * Method for a User to find all his friends by DaoUser id.
     * @param id DaoUser id.
     * @return A Set of DaoUser.
     */
    @Override
    public Set<DaoApplicationUser> findAllMyFriends(int id) {
        HashSet<DaoApplicationUser> friendsSet = new HashSet<>();
        Optional<DaoApplicationUser> user = applicationuserRepository.findById(id);
        List<Friends> friends = friendsRepository.findAll();
        if (user.isPresent()) {
            for (Friends friend : friends) {
                // Others add me as friend:
                if (friend.getFriend().getId() == id) {
                    friendsSet.add(friend.getUser());
                    // I add other as friend:
                } else if (friend.getUser().getId() == id) {
                    friendsSet.add(friend.getFriend());
                }
            }
        }
        return friendsSet;
    }

}
