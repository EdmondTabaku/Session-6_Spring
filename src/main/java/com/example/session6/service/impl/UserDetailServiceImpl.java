package com.example.session6.service.impl;

import com.example.session6.dto.UserDetailDTO;
import com.example.session6.model.UserDetail;

import com.example.session6.repository.UserDetailsRepository;
import com.example.session6.service.UserDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailServiceImpl implements UserDetailService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    // Saving user details
    @Override
    public UserDetailDTO save(UserDetail userDetail) {
        return convertToDTO(userDetailsRepository.save(userDetail));
    }

    // Finding all the user details
    @Override
    public List<UserDetailDTO> findAll() {

        List<UserDetailDTO> userDetailDTOList = userDetailsRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
        return userDetailDTOList;
    }

    // Finding a user detail by id
    @Override
    public UserDetailDTO findById(int id) {

        UserDetail userDetail = new UserDetail();
        Optional<UserDetail> userDetailsOptional = userDetailsRepository.findById(id);

        if (userDetailsOptional.isPresent()){
            userDetail = userDetailsOptional.get();
        }

        return convertToDTO(userDetail);
    }

    // Deleting a user datail
    @Override
    public void delete(int id) {
        userDetailsRepository.deleteById(id);
    }

    @Override
    public UserDetailDTO convertToDTO(UserDetail userDetail) {
        UserDetailDTO userDetailDTO = new UserDetailDTO();

        userDetailDTO.setFirstName(userDetail.getFirstName());
        userDetailDTO.setLastName(userDetail.getLastName());
        userDetailDTO.setPhone(userDetail.getPhoneNumber());
        userDetailDTO.setEmail(userDetail.getEmail());

        return userDetailDTO;
    }
}
