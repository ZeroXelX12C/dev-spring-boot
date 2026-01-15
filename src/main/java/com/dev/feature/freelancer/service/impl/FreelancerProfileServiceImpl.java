package com.dev.feature.freelancer.service.impl;

import com.dev.feature.freelancer.dto.request.ProfileRequest;
import com.dev.feature.freelancer.dto.response.ProfileResponse;
import com.dev.exception.ResourceNotFoundException;
import com.dev.model.Freelancer;
import com.dev.model.User;
import com.dev.feature.freelancer.repository.FreelancerRepository;
import com.dev.feature.auth.repository.UserRepository;
import com.dev.feature.freelancer.service.FreelancerProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreelancerProfileServiceImpl implements FreelancerProfileService {

    private final FreelancerRepository freelancerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProfileResponse getMyProfile(String userId) {
        Freelancer freelancer = getFreelancerByUserId(userId);
        User user = getUserById(userId);
        return mapToProfileResponse(freelancer, user);
    }

    @Override
    public ProfileResponse getPublicProfile(String userId) {
        Freelancer freelancer = getFreelancerByUserId(userId);
        User user = getUserById(userId);
        return mapToProfileResponse(freelancer, user);
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(String userId, ProfileRequest request) {
        Freelancer freelancer = freelancerRepository.findByUserId(userId)
                .orElse(Freelancer.builder().userId(userId).build());

        modelMapper.map(request, freelancer);
        freelancer.setUserId(userId);
        Freelancer savedFreelancer = freelancerRepository.save(freelancer);

        User user = getUserById(userId);

        boolean isUserUpdated = false;

        if (request.getFullName() != null && !request.getFullName().isBlank()) {
            user.setFullName(request.getFullName());
            isUserUpdated = true;
        }
        if (request.getAvatar() != null && !request.getAvatar().isBlank()) {
            user.setAvatar(request.getAvatar());
            isUserUpdated = true;
        }

        if (isUserUpdated) {
            userRepository.save(user);
        }

        return mapToProfileResponse(savedFreelancer, user);
    }

    /**
     * Helper method to get freelancer by userId.
     */
    private Freelancer getFreelancerByUserId(String userId) {
        return freelancerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not found"));
    }

    /**
     * Helper method to get user by userId.
     */
    private User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Helper method to map Freelancer and User to ProfileResponse.
     */
    private ProfileResponse mapToProfileResponse(Freelancer freelancer, User user) {
        ProfileResponse response = modelMapper.map(freelancer, ProfileResponse.class);

        response.setFullName(user.getFullName());
        response.setAvatar(user.getAvatar());
        response.setUserId(user.getId());

        return response;
    }
}