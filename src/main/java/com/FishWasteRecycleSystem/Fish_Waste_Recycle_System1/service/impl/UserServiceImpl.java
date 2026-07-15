package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.UserRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.UserService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


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
        User user=userRepository.findById(id).orElseThrow(()->new IllegalArgumentException("user not found with id"+id));
        return modelMapper.map(user,UserDto.class);
    }
    @Override
    public UserDto createNewUser(UserRequestDto addUserRequestDto) {
        User newUser=modelMapper.map(addUserRequestDto,User.class);
        User user=userRepository.save(newUser);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id))
        {
            throw new IllegalArgumentException("Student does not exist by id:" +id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user=userRepository.findById(id).
                orElseThrow(()->new IllegalArgumentException("User not found with id"+id));
        modelMapper.map(userRequestDto,user);
        userRepository.save(user);

        user=userRepository.save(user);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updatePartialUser(Long id, Map<String, Object> updates) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found with id: " + id));

        updates.forEach((field, value) -> {

            switch (field) {

                case "name":
                    user.setName((String) value);
                    break;

                case "email":
                    user.setEmail((String) value);
                    break;

                case "phoneNo":
                    user.setPhoneNo(((Number) value).longValue());
                    break;

                case "password":
                case "role":
                case "id":
                    throw new IllegalArgumentException(field + " cannot be updated using PATCH API.");

                default:
                    throw new IllegalArgumentException("Field '" + field + "' is not supported.");
            }
        });

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDto.class);
    }

}
