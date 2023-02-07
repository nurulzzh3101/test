package com.latihan.test.repository;

import com.latihan.test.entity.UserParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPartyRepository extends JpaRepository<UserParty, String> {
    List<UserParty> findByTypeId(String typeId);
    List<UserParty> findByRoleId(String roleId);
    UserParty findByUserId(String userId);
}
