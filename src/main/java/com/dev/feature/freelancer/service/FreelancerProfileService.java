package com.dev.feature.freelancer.service;

import com.dev.feature.freelancer.dto.request.ProfileRequest;
import com.dev.feature.freelancer.dto.response.ProfileResponse;

public interface FreelancerProfileService {

    ProfileResponse getMyProfile(String userId);

    ProfileResponse getPublicProfile(String userId);

    ProfileResponse updateProfile(String userId, ProfileRequest request);
}