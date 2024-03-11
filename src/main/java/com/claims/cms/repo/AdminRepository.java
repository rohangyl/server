package com.claims.cms.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claims.cms.entity.Admin;
import com.claims.cms.request.AdminRequest;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUsername(String Username);

	Admin save(AdminRequest ar);

	Optional<Admin> findByUsernameAndPassword(String username, String password);

	
}




