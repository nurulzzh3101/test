package com.latihan.test.repository;

import com.latihan.test.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, String> {
    RoleUser findByName(String name);
    List<RoleUser> findByIsDeleted(Boolean isDeleted);
}
