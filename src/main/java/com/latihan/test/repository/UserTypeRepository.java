package com.latihan.test.repository;

import com.latihan.test.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, String> {
    UserType findByName(String name);
    List<UserType> findByIsDeleted(Boolean isDeleted);
}
