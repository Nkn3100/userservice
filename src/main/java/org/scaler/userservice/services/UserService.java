package org.scaler.userservice.services;


import org.scaler.userservice.dtos.SignUpRequestDto;
import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.TokenAlreadyExpiredOrNotFoundException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.Token;
import org.scaler.userservice.models.User;

import java.util.List;

public interface UserService {
    User getUser(Long id) throws UserNotFoundException;
    List<User> getAllUsers();
    User createUser(User user);
    User updateUser(Long id, User user) throws UserNotFoundException;
    void deleteUser(Long id) throws UserNotFoundException;
    Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException;
    User signUp(SignUpRequestDto signUpRequestDto);

    void logout(String token) throws TokenAlreadyExpiredOrNotFoundException;
    User validateToken(String token) throws TokenAlreadyExpiredOrNotFoundException;


}
