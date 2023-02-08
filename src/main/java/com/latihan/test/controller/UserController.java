package com.latihan.test.controller;

import com.latihan.test.dto.UserDto;
import com.latihan.test.entity.User;
import com.latihan.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<User> register (@RequestBody Map<String,Object> body) throws ValidationException{
        User user = userService.register(body);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/login")
    public  ResponseEntity<User> login (@RequestBody Map<String,Object> body) throws ValidationException{
        User user = userService.login(body);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<User> logout (@RequestBody Map<String,Object> body) throws ValidationException{
        userService.logout(body);
        return ResponseEntity.ok().body(new User());
    }

    @GetMapping(value = "/get/{id}")
    public ResponseEntity<User> getById (@PathParam("id") String userId) throws ValidationException{
        User user = userService.findByIdUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<List<UserDto>> getAll () throws ValidationException{
        List<UserDto> user = userService.findAllUser();
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<User> update (@RequestBody Map<String,Object> body) throws ValidationException{
        User user = userService.updateUser(body);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<User> delete (@PathParam("id") String userId) throws ValidationException{
        userService.deleteUser(userId);
        return ResponseEntity.ok().body(new User());
    }
}
