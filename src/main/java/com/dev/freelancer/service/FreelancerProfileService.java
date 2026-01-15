package com.dev.freelancer.service;

import com.dev.freelancer.dto.request.ProfileRequest;
import com.dev.freelancer.dto.response.ProfileResponse;

public interface FreelancerProfileService {

    ProfileResponse getMyProfile(String userId);

    ProfileResponse getPublicProfile(String userId);

    ProfileResponse updateProfile(String userId, ProfileRequest request);
}