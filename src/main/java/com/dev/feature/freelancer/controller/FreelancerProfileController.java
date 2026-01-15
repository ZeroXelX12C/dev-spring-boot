package com.dev.feature.freelancer.controller;

import com.dev.feature.freelancer.dto.request.ProfileRequest;
import com.dev.feature.freelancer.dto.response.ProfileResponse;
import com.dev.feature.freelancer.service.FreelancerProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/freelancers")
@RequiredArgsConstructor
public class FreelancerProfileController {

    private final FreelancerProfileService profileService;

    private String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> getMyProfile() {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(profileService.getMyProfile(userId));
    }

    @PutMapping("/profile")
    public ResponseEntity<ProfileResponse> updateProfile(@Valid @RequestBody ProfileRequest request) {
        String userId = getCurrentUserId();
        return ResponseEntity.ok(profileService.updateProfile(userId, request));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<ProfileResponse> getPublicProfile(@PathVariable String id) {
        return ResponseEntity.ok(profileService.getPublicProfile(id));
    }
}