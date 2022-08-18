package com.latihan.stream.Repository;

import com.latihan.stream.Entity.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepostory extends CrudRepository<Roles,Long> {
    @Query("SELECT u FROM Roles u WHERE u.id_roles = ?1")
     Roles findOneId(Long id);
}
