package com.dev.freelancer.service.impl;

import com.dev.freelancer.dto.request.ProfileRequest;
import com.dev.freelancer.dto.response.ProfileResponse;
import com.dev.exception.ResourceNotFoundException;
import com.dev.model.Freelancer;
import com.dev.model.User;
import com.dev.freelancer.repository.FreelancerRepository;
import com.dev.auth.repository.UserRepository;
import com.dev.freelancer.service.FreelancerProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FreelancerProfileProfileServiceImpl implements FreelancerProfileService {

    private final FreelancerRepository freelancerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProfileResponse getMyProfile(String userId) {
        Freelancer freelancer = freelancerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not yet created"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return mapToProfileResponse(freelancer, user);
    }

    @Override
    public ProfileResponse getPublicProfile(String userId) {
        Freelancer freelancer = freelancerRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer profile not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

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

    private ProfileResponse mapToProfileResponse(Freelancer freelancer, User user) {
        ProfileResponse response = modelMapper.map(freelancer, ProfileResponse.class);

        response.setFullName(user.getFullName());
        response.setAvatar(user.getAvatar());

        response.setUserId(user.getId());

        return response;
    }
}