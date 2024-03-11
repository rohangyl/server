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

import com.claims.cms.entity.PolicyHolder;
import com.claims.cms.service.PolicyHolderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins="http://localhost:3002")
@RequestMapping("/api/policyholders")
@Api(value = "Policy Holder Controller", tags = "Policy Holder Management")

public class PolicyHolderController {

    @Autowired
    private PolicyHolderService policyHolderService;

    @GetMapping
    @ApiOperation(value = "Get all policy holders", notes = "Retrieve a list of all policy holders.")

    public ResponseEntity<List<PolicyHolder>> getAllPolicyHolders() {
        List<PolicyHolder> policyHolders = policyHolderService.getAllPolicyHolders();
        return ResponseEntity.ok(policyHolders);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get all policy holders by id", notes = "Retrieve a list of all policy holders.")

    public ResponseEntity<PolicyHolder> getPolicyHolderById(@PathVariable Long id) {
        PolicyHolder policyHolder = policyHolderService.getPolicyHolderById(id);
        return ResponseEntity.ok(policyHolder);
    }

    @PostMapping("/create")
    @ApiOperation(value = "create all policy holders", notes = "Retrieve a list of all policy holders.")

    public ResponseEntity<PolicyHolder> createPolicyHolder(@RequestBody PolicyHolder policyHolder) {
        PolicyHolder createdPolicyHolder = policyHolderService.createPolicyHolder(policyHolder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicyHolder);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "update all policy holders", notes = "Retrieve a list of all policy holders.")

    public ResponseEntity<PolicyHolder> updatePolicyHolder(@PathVariable Long id, @RequestBody PolicyHolder policyHolder) {
        PolicyHolder updatedPolicyHolder = policyHolderService.updatePolicyHolder(id, policyHolder);
        return ResponseEntity.ok(updatedPolicyHolder);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete all policy holders", notes = "Retrieve a list of all policy holders.")

    public ResponseEntity<Void> deletePolicyHolder(@PathVariable Long id) {
        policyHolderService.deletePolicyHolder(id);
        return ResponseEntity.noContent().build();
    }

   

}



