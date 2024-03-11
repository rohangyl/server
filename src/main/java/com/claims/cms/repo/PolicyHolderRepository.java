package com.claims.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claims.cms.entity.PolicyHolder;

@Repository

public interface PolicyHolderRepository extends JpaRepository<PolicyHolder, Long> {
}




