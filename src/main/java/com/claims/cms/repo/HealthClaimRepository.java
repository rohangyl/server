package com.claims.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claims.cms.entity.Admin;
import com.claims.cms.entity.HealthClaim;
import com.claims.cms.entity.User;
@Repository

public interface HealthClaimRepository extends JpaRepository<HealthClaim, Long> {

	List<HealthClaim> findByAdmin(Admin admin);

	List<HealthClaim> findByUser(User user);
}


