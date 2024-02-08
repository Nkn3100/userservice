package org.scaler.userservice.controllers;

import org.scaler.userservice.dtos.LoginRequestDto;
import org.scaler.userservice.dtos.LogoutRequestDto;
import org.scaler.userservice.dtos.SignUpRequestDto;
import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.TokenAlreadyExpiredOrNotFoundException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.Name;
import org.scaler.userservice.models.Token;
import org.scaler.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.scaler.userservice.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws UserNotFoundException {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
        userService.deleteUser(id);
        return ResponseEntity.ok("User with id: "+id+" deleted successfully.");
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidPasswordException {
        // check if email and password in db
        // if yes return user
        // else throw some error
        return userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @PostMapping("/signup")
    public User signUp(@RequestBody SignUpRequestDto signUpRequestDto){
        // no need to hash password for now
        // just store user as is in the db
        // for now no need to have email verification either
        // just store user and return user

        return userService.signUp(signUpRequestDto);

    }
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws TokenAlreadyExpiredOrNotFoundException {
        userService.logout(logoutRequestDto.getToken());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/validate/{token}")
    public User validateToken(@PathVariable("token") @NonNull String token) throws TokenAlreadyExpiredOrNotFoundException {
        return userService.validateToken(token);
    }
}
