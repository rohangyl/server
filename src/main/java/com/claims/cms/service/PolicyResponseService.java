package com.claims.cms.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claims.cms.entity.PolicyResponse;
import com.claims.cms.repo.PolicyResponseRepository;



@Service
public class PolicyResponseService {

	 @Autowired
	    private PolicyResponseRepository policyResponseRepository;

	    public List<PolicyResponse> getAllPolicyResponses() {
	        return policyResponseRepository.findAll();
	    }

	    public PolicyResponse getPolicyResponseById(Long policyResponseId) {
	        return policyResponseRepository.findById(policyResponseId)
	                .orElseThrow(() -> new NoSuchElementException("PolicyResponse not found"));
	    }

	    public PolicyResponse createPolicyResponse(PolicyResponse policyResponse) {
	        // Implement validation logic if needed
	        return policyResponseRepository.save(policyResponse);
	    }

	    public PolicyResponse updatePolicyResponse(Long policyResponseId, PolicyResponse updatedPolicyResponse) {
	        PolicyResponse existingPolicyResponse = getPolicyResponseById(policyResponseId);

	        // Update properties as needed
	        existingPolicyResponse.setStatus(updatedPolicyResponse.getStatus());
	        existingPolicyResponse.setResponseDetails(updatedPolicyResponse.getResponseDetails());
	        // Update other properties...

	        return policyResponseRepository.save(existingPolicyResponse);
	    }

	    public void deletePolicyResponse(Long policyResponseId) {
	        policyResponseRepository.deleteById(policyResponseId);
	    }

	   

	
}



