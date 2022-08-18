package com.latihan.stream.Service;

import com.latihan.stream.Entity.Permission;
import com.latihan.stream.Repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PermissionServices {
    @Autowired
    PermissionRepository permissionRepository;

    public Permission createPermission(Permission permission){
        return  permissionRepository.save(permission);
    }
}
