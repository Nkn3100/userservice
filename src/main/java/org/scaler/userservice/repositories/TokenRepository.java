package org.scaler.userservice.repositories;

import org.scaler.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByTokenAndDeleted(String token, boolean isDeleted);
    Optional<Token> findByTokenAndDeletedAndExpiryDateGreaterThan(String token, boolean isDeleted, Date date);

}
