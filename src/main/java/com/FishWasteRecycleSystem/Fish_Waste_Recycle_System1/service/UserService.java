package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserRequestDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserDto> getAllUser();

    UserDto getUserById(Long id);

    UserDto createNewUser(UserRequestDto userRequestDto);

    void deleteUserById(Long id);

    UserDto updateUser(Long id, UserRequestDto userRequestDto);

    UserDto updatePartialUser(Long id, Map<String,Object> updates);
}
