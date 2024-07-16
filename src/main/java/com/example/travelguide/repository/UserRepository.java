package com.example.travelguide.repository;

import com.example.travelguide.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing User data.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by username.
     * @param username the username of the user to find.
     * @return the user with the specified username.
     */
    User findByUsername(String username);
}
