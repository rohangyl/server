package com.claims.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claims.cms.entity.PolicyResponse;

@Repository
public interface PolicyResponseRepository extends JpaRepository<PolicyResponse, Long> {
}



