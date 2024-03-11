package com.claims.cms.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claims.cms.entity.PolicyHolder;
import com.claims.cms.repo.PolicyHolderRepository;



@Service
public class PolicyHolderService {

    @Autowired
    private PolicyHolderRepository policyHolderRepository;

        public List<PolicyHolder> getAllPolicyHolders() {
            return policyHolderRepository.findAll();
        }

        public PolicyHolder getPolicyHolderById(Long policyHolderId) {
            return policyHolderRepository.findById(policyHolderId)
                    .orElseThrow(() -> new NoSuchElementException("PolicyHolder not found"));
        }

        public PolicyHolder createPolicyHolder(PolicyHolder policyHolder) {
            // Implement validation logic if needed
            return policyHolderRepository.save(policyHolder);
        }

        public PolicyHolder updatePolicyHolder(Long policyHolderId, PolicyHolder updatedPolicyHolder) {
            PolicyHolder existingPolicyHolder = getPolicyHolderById(policyHolderId);

            
            existingPolicyHolder.setName(updatedPolicyHolder.getName());
            existingPolicyHolder.setAddress(updatedPolicyHolder.getAddress());
            existingPolicyHolder.setContactDetails(updatedPolicyHolder.getContactDetails());
            return policyHolderRepository.save(existingPolicyHolder);
        }

        public void deletePolicyHolder(Long policyHolderId) {
            policyHolderRepository.deleteById(policyHolderId);
        }

    
    
}



