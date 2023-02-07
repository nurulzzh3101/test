package com.latihan.test.repository;

import com.latihan.test.entity.ActivityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityUser, String> {
    List<ActivityUser> findByUserId(String userId);
    List<ActivityUser> findByType(String type);
    List<ActivityUser> findByDesc(String desc);
    ActivityUser findActivity(String userId, String type, String desc);
}
