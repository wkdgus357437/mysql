package com.main.bitfinal.memberService.repository;

import com.main.bitfinal.memberService.memberEntity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username); // user 조회

    boolean existsByUsername(String username); // duplication chk

    Optional<User> findByPhoneNumber(String phoneNumber);

    void deleteByUsername(String username);

    @Query("select user.username from User user where user.name = ?1 and user.birth = ?2 and user.phoneNumber = ?3 ")
    String findByMyId(String name, String birth, String phoneNumber);

    @Query("select user from User user where user.username = ?1")
    User findByUsername2(String username);

    @Query("select user from User user where user.username = ?1 and user.name = ?2 and user.birth = ?3 and user.phoneNumber = ?4")
    Optional<User> findByPasswordUserInfo(String username, String name, String birth, String phoneNumber);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.password = ?2 where u.username = ?1")
    void changePassword(String username, String password);

    @Query("select u.password from User u where u.username = ?1")
    String findByPassword(String username);
}
