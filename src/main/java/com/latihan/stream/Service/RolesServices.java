package com.latihan.stream.Service;

import com.latihan.stream.Entity.Permission;
import com.latihan.stream.Entity.Roles;
import com.latihan.stream.Repository.PermissionRepository;
import com.latihan.stream.Repository.RolesRepostory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class RolesServices {
    @Autowired
    private RolesRepostory rolesRepostory;
    @Autowired
    private PermissionRepository permissionRepository;
    public Roles CreateRoles(Roles roles){
        return rolesRepostory.save(roles);
    }


    public void  assignPermission(Map<String,Object> body) {
        Long convertedLong = Long.parseLong(body.get("id_roles").toString());
        Roles roles = rolesRepostory.findOneId(convertedLong);
        List<Object> list = new ArrayList<>();
        if (body.get("permission").getClass().isArray()){
            list = Arrays.asList((Object[])body.get("permission"));
        }else if (body.get("permission") instanceof Collection) {
            list = new ArrayList<>((Collection<?>)body.get("permission"));
        }
        Set<Permission> permissions = new HashSet<>();
        list.stream().forEach(value -> {
            Permission permission = permissionRepository.findOneName(value.toString());
            if (permission != null){
                permissions.add(permission);
                System.out.println(permission);
                roles.setPermission(permissions);
            }
        });
        rolesRepostory.save(roles);



    }

    public Iterable<Roles> findAll() {
        return rolesRepostory.findAll();
    }
}
