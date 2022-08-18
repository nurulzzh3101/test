package com.latihan.stream.Controller;

import com.latihan.stream.Entity.Permission;
import com.latihan.stream.Entity.Roles;
import com.latihan.stream.Service.RolesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private RolesServices rolesServices;
    @RequestMapping("/created")
    private ResponseEntity<?> createRoles(@RequestBody  Roles roles){
        Roles roles1 = rolesServices.CreateRoles(roles);
        return ResponseEntity.ok().body(roles1);
    }
    @PostMapping("/assign")
    private ResponseEntity<?> AssignPermission(@RequestBody  Map<String,Object> roles){
        rolesServices.assignPermission(roles);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/")
    private ResponseEntity<?> getRoles(){
        Iterable<Roles>  roles= rolesServices.findAll();
        return ResponseEntity.ok().body(roles);
    }
}
