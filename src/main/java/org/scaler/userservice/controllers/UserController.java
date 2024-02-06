package org.scaler.userservice.controllers;

import org.scaler.userservice.dtos.LoginRequestDto;
import org.scaler.userservice.dtos.SignUpRequestDto;
import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.Name;
import org.scaler.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.scaler.userservice.services.UserService;

import java.util.List;

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

    @GetMapping("/login")
    public User login(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException, InvalidPasswordException {
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
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setHashedPassword(signUpRequestDto.getPassword());
        Name name = new Name();
        name.setFirstName(signUpRequestDto.getName());
        user.setName(name);
        return userService.createUser(user);

    }
    public ResponseEntity<Void> logout(){
        //TODO: implement logout
        return null;
    }
}
