package org.scaler.userservice.services;

import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.scaler.userservice.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SelfUserService implements UserService{

    private UserRepository userRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with id: "+id+" doesn't exist.");
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);

    }

    @Override
    public User updateUser(Long id, User user) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        User userFromDB = getUser(user, userOptional, id);
        return userRepository.save(userFromDB);
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()){
            userRepository.delete(userOptional.get());
            return;
        }
        throw new UserNotFoundException("User with id: "+id+" doesn't exist.");
    }
    private static User getUser(User user, Optional<User> userOptional, Long id) throws UserNotFoundException {
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with id: "+id+" doesn't exist.");
        }
        User userFromDB = userOptional.get();
        if(user.getName()!=null && !user.getName().equals(userFromDB.getName())){
            userFromDB.setName(user.getName());
        }
        if(user.getEmail()!=null && !user.getEmail().equals(userFromDB.getEmail())){
            userFromDB.setEmail(user.getEmail());
        }
        if(user.getPhone()!=null && !user.getPhone().equals(userFromDB.getPhone())){
            userFromDB.setPhone(user.getPhone());
        }
        if (user.getHashedPassword()!=null && !user.getHashedPassword().equals(userFromDB.getHashedPassword())){
            userFromDB.setHashedPassword(user.getHashedPassword());
        }
        if (user.getUsername()!=null && !user.getUsername().equals(userFromDB.getUsername())){
            userFromDB.setUsername(user.getUsername());
        }
        if (user.getAddress()!=null && !user.getAddress().equals(userFromDB.getAddress())){
            userFromDB.setAddress(user.getAddress());
        }
        return userFromDB;
    }
    @Override
    public User login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with email: "+email+" doesn't exist.");
        }
        User user = userOptional.get();
        if(user.getHashedPassword().equals(password)){
            return user;
        }
        throw new InvalidPasswordException("Invalid password for email: "+email);
    }
}
