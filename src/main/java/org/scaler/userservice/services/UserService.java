package org.scaler.userservice.services;


import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.User;

import java.util.List;

public interface UserService {
    User getUser(Long id) throws UserNotFoundException;
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
    User login(String email, String password) throws UserNotFoundException, InvalidPasswordException;
}
