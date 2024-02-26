package com.adtavera.apicarfix.controllers;

import com.adtavera.apicarfix.dtos.UserDto;
import com.adtavera.apicarfix.dtos.UserUpdateDto;
import com.adtavera.apicarfix.models.User;
import com.adtavera.apicarfix.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") Long id
    ) {
        User user = this.userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.create(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserUpdateDto userUpdateDto
            ) {
        return ResponseEntity.ok(this.userService.update(id, userUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(this.userService.deleteById(id));
    }
}
