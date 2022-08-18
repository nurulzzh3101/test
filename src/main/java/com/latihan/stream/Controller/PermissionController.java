package com.latihan.stream.Controller;

import com.latihan.stream.Entity.Permission;
import com.latihan.stream.Service.PermissionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    PermissionServices permissionServices;
    @PostMapping("/created")
    private ResponseEntity<?> CreatePermission(@RequestBody Permission permission){
        return ResponseEntity.ok().body(permissionServices.createPermission(permission));
    }

}
