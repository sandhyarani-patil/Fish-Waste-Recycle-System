package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

      private final UserService userService;

      @GetMapping
      public ResponseEntity<List<UserDto>> getAllUser(){

          return ResponseEntity.ok(userService.getAllUser());
      }

      @GetMapping("/{id}")
      public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

          return ResponseEntity.ok(userService.getUserById(id));
      }

      @PostMapping
      public ResponseEntity<UserDto> createNewUser(@RequestBody @Valid UserRequestDto userRequestDto)
      {
          UserDto userdto=userService.createNewUser(userRequestDto);
          return ResponseEntity.status(HttpStatus.CREATED)
                  .body(userdto);
      }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUserById(id);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserRequestDto UserRequestDto)
    {
        return ResponseEntity.ok(userService.updateUser(id,UserRequestDto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updatePartialUser(@PathVariable Long id, @RequestBody Map<String,Object> updates)
    {
        return ResponseEntity.ok(userService.updatePartialUser(id,updates));
    }
}



