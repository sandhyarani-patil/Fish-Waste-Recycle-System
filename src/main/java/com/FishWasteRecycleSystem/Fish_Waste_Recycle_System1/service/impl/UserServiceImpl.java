package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.BadRequestException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.ResourceNotFoundException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.DuplicateResourceException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.UserRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.UserService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    public List<UserDto> getAllUser(){

        List<User> users=userRepository.findAll();

        List <UserDto> usertDtoList=users
                .stream()
                .map(user->modelMapper.map(user,UserDto.class))
                .toList();
        return usertDtoList;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        return modelMapper.map(user,UserDto.class);
    }
    @Override
    public UserDto createNewUser(UserRequestDto userRequestDto) {

        User newUser = modelMapper.map(userRequestDto, User.class);

        newUser.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, UserDto.class);
    }
    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id))
        {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserRequestDto userRequestDto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));

        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPhoneNo(userRequestDto.getPhoneNo());
        user.setRole(userRequestDto.getRole());

        // Password encrypt करून save कर
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }
    @Override
    public UserDto updatePartialUser(Long id, Map<String, Object> updates) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
        updates.forEach((field, value) -> {

            switch (field) {

                case "name":
                    user.setName((String) value);
                    break;

                case "email":
                    user.setEmail((String) value);
                    break;

                case "phoneNo":
                    user.setPhoneNo((String) value);
                    break;

                case "password":
                case "role":
                case "id":
                    throw new BadRequestException(field + " cannot be updated using PATCH API.");

                default:
                    throw new BadRequestException("Field '" + field + "' is not supported.");
            }
        });

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

}
