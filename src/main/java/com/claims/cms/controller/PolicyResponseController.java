package com.claims.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claims.cms.entity.PolicyResponse;
import com.claims.cms.service.PolicyResponseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins="http://localhost:3002")
@RequestMapping("/api/policyresponses")
@Api(value = "Policy Response Controller", tags = "Policy Response Management")

public class PolicyResponseController {

    @Autowired
    private PolicyResponseService policyResponseService;

    @GetMapping
    @ApiOperation(value = "Get all policy responses", notes = "Retrieve a list of all policy responses.")

    public ResponseEntity<List<PolicyResponse>> getAllPolicyResponses() {
        List<PolicyResponse> policyResponses = policyResponseService.getAllPolicyResponses();
        return ResponseEntity.ok(policyResponses);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all policy responses by id", notes = "Retrieve a list of all policy responses.")

    public ResponseEntity<PolicyResponse> getPolicyResponseById(@PathVariable Long id) {
        PolicyResponse policyResponse = policyResponseService.getPolicyResponseById(id);
        return ResponseEntity.ok(policyResponse);
    }

    @PostMapping("/create")
    @ApiOperation(value = "create all policy responses", notes = "Retrieve a list of all policy responses.")

    public ResponseEntity<PolicyResponse> createPolicyResponse(@RequestBody PolicyResponse policyResponse) {
        PolicyResponse createdPolicyResponse = policyResponseService.createPolicyResponse(policyResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyResponse);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update all policy responses", notes = "Retrieve a list of all policy responses.")

    public ResponseEntity<PolicyResponse> updatePolicyResponse(@PathVariable Long id, @RequestBody PolicyResponse policyResponse) {
        PolicyResponse updatedPolicyResponse = policyResponseService.updatePolicyResponse(id, policyResponse);
        return ResponseEntity.ok(updatedPolicyResponse);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete all policy responses", notes = "Retrieve a list of all policy responses.")

    public ResponseEntity<Void> deletePolicyResponse(@PathVariable Long id) {
        policyResponseService.deletePolicyResponse(id);
        return ResponseEntity.noContent().build();
    }

    // Additional endpoints for policy response management can be added here

}



