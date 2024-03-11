package com.claims.cms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claims.cms.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	Optional <User> findByUsernameAndPassword(String username, String password);

	Optional <User> findByUsername(String username);
}

