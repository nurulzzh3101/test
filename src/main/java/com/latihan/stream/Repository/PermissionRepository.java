package com.latihan.stream.Repository;

import com.latihan.stream.Entity.Permission;
import com.latihan.stream.Entity.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long> {
    @Query("SELECT u FROM Permission u WHERE u.name = ?1")
    Permission findOneName(String id);
}
