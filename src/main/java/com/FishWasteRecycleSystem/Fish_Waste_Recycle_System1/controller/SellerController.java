package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.controller;


import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.UserDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.SellerService;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping
    public ResponseEntity<List<SellerDto>> getAllSellers(){
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<SellerDto> getSellerById(@PathVariable Long sellerId){
        return ResponseEntity.ok(sellerService.getSellerById(sellerId));
    }

    @PostMapping
    public ResponseEntity<SellerDto> createNewSeller(@RequestBody @Valid SellerRequestDto sellerRequestDto)
    {
        SellerDto sellerDto=sellerService.createNewSeller(sellerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sellerDto);
    }

    @DeleteMapping("/{sellerId}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long sellerId)
    {
        sellerService.deleteSellerById(sellerId);
        return  ResponseEntity.noContent().build();
    }

    @PutMapping("/{sellerId}")
    public ResponseEntity<SellerDto> updateSeller(@PathVariable Long sellerId,@Valid @RequestBody SellerRequestDto sellerRequestDto)
    {
        return ResponseEntity.ok(sellerService.updateSeller(sellerId,sellerRequestDto));
    }


    @PatchMapping("/{sellerId}")
    public ResponseEntity<SellerDto> updatePartialSeller(@PathVariable Long sellerId, @RequestBody Map<String,Object> updates)
    {
        return ResponseEntity.ok(sellerService.updatePartialSeller(sellerId,updates));
    }


}
