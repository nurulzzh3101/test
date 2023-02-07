package com.latihan.test.repository;

import com.latihan.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findUserById(String id);
    User findUserByEmail(String email);
    User findUserByPhone(String phone);
    User findUserByNik(String nik);
    User findByName(String name);
    User findByIsDeleted(Boolean isDeleted);
    List<User> findAllByIsDeleted(Boolean isDeleted);
    User findUser(String email, String phone, String nik, Boolean isDeeleted);
    User findUserNew(String email, String phone, String nik);
    User findByEmailPassword(String email, String password);
}
