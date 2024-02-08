package org.scaler.userservice.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.scaler.userservice.dtos.SignUpRequestDto;
import org.scaler.userservice.exceptions.InvalidPasswordException;
import org.scaler.userservice.exceptions.TokenAlreadyExpiredOrNotFoundException;
import org.scaler.userservice.exceptions.UserNotFoundException;
import org.scaler.userservice.models.Name;
import org.scaler.userservice.models.Token;
import org.scaler.userservice.models.User;
import org.scaler.userservice.repositories.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.scaler.userservice.repositories.UserRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SelfUserService implements UserService{

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private TokenRepository tokenRepository;

    @Autowired
    public SelfUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
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
    public User signUp(SignUpRequestDto signUpRequestDto){
        User user = new User();
        user.setEmail(signUpRequestDto.getEmail());
        user.setHashedPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
        Name name = new Name();
        name.setFirstName(signUpRequestDto.getName());
        user.setName(name);
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
    public Token login(String email, String password) throws UserNotFoundException, InvalidPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with email: "+email+" doesn't exist.");
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password, user.getHashedPassword())){
            throw new InvalidPasswordException("Invalid password for email: "+email);
        }
        Token token = new Token();
        token.setUser(user);
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysLater = today.plusDays(30);
        Date expiryDate = Date.from(thirtyDaysLater.atStartOfDay(ZoneId.systemDefault()).toInstant());
        token.setExpiryDate(expiryDate);
        token.setToken(RandomStringUtils.randomAlphabetic(128));
        tokenRepository.save(token);
        return token;
    }
    @Override
    public void logout(String token) throws TokenAlreadyExpiredOrNotFoundException {
        Optional<Token> tokenOptional = tokenRepository.findByTokenAndDeleted(token, false);
        if(tokenOptional.isPresent()){
            Token tokenFromDB = tokenOptional.get();
            tokenFromDB.setDeleted(true);
            tokenRepository.save(tokenFromDB);
        }else{
            throw new TokenAlreadyExpiredOrNotFoundException("Token is already expired or not found.");
        }
    }

    @Override
    public User validateToken(String token) throws TokenAlreadyExpiredOrNotFoundException {
        Optional<Token> tokenOptional = tokenRepository.findByTokenAndDeletedAndExpiryDateGreaterThan(token, false, new Date());
        if (tokenOptional.isPresent()) {
            return tokenOptional.get().getUser();
        }
        throw new TokenAlreadyExpiredOrNotFoundException("Token is already expired or not found.");
    }
}
