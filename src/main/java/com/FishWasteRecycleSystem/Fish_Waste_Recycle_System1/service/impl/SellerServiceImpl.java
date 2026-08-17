package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.impl;

import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.BadRequestException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.DuplicateResourceException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.exception.ResourceNotFoundException;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.dto.SellerRequestDto;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.Seller;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.SellerRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.repository.UserRepository;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.entity.User;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.enums.Role;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
//    Request me userId aayega.
//    UserRepository se user fetch hoga.
//    Check karna:
//    User exist karta hai?
//    User ka role SELLER hai?
//    Tab Seller entity create hogi.
//    Seller ko save karoge.
//
//    Isse koi COMPANY ya ADMIN us

    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public List<SellerDto> getAllSellers(){

        List<Seller> sellers=sellerRepository.findAll();

        List <SellerDto> sellerDtoList=sellers
                .stream()
                .map(seller->modelMapper.map(seller,SellerDto.class))
                .toList();
        return sellerDtoList;
    }

    @Override
    public SellerDto getSellerById(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id: " + sellerId));
        return modelMapper.map(seller,SellerDto.class);
    }

    @Override
    public SellerDto createNewSeller(SellerRequestDto sellerRequestDto) {

        // १. युझर शोधणे
        User user = userRepository.findById(sellerRequestDto.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + sellerRequestDto.getUserId()));



        // २. रोल चेक करणे
        if (user.getRole() != Role.SELLER) {
            throw new BadRequestException("User is not registered as a seller");
        }

        if (sellerRepository.existsByUser_Id(sellerRequestDto.getUserId())) {
            throw new DuplicateResourceException("Seller profile already exists for this user.");
        }

        // ३. मॅन्युअली नवीन Seller ऑब्जेक्ट तयार करणे (ModelMapper न वापरता)
        Seller newSeller = new Seller();

        // स्पेलिंग तपासून घ्या: जर DTO मध्ये 'adress' असेल तर getAdress() वापरा, 'address' असेल तर getAddress() वापरा.
        newSeller.setAddress(sellerRequestDto.getAddress());
        newSeller.setShopName(sellerRequestDto.getShopName());
        newSeller.setAvailableFishWasteKg(sellerRequestDto.getAvailableFishWasteKg());


        // युझर मॅप करणे
        newSeller.setUser(user);

        // ४. सेव्ह करणे
        Seller savedSeller = sellerRepository.save(newSeller);

        // ५. रिस्पॉन्ससाठी ModelMapper वापरण्यास हरकत नाही
        return modelMapper.map(savedSeller, SellerDto.class);
    }

    @Override
    public void deleteSellerById(Long sellerId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new ResourceNotFoundException("Seller not found with id: " + sellerId);
        }
        sellerRepository.deleteById(sellerId);
    }

    @Override
    public SellerDto updateSeller(Long sellerId, SellerRequestDto sellerRequestDto) {
        // 1. डेटाबेसमधून जुना सेलर शोधा
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id: " + sellerId));
        // 2. ModelMapper ऐवजी मॅन्युअली फक्त आवश्यक डेटा सेट करा
        // (लक्षात घ्या: आपण इथे आयडी बदलत नाही आहोत!)
        seller.setShopName(sellerRequestDto.getShopName());
        seller.setAddress(sellerRequestDto.getAddress());
        seller.setAvailableFishWasteKg(sellerRequestDto.getAvailableFishWasteKg());

        // 3. डेटाबेसमध्ये सेव्ह करा
        Seller updatedSeller = sellerRepository.save(seller);

        // 4. रिटर्न करताना ModelMapper वापरू शकता (यात अडचण येत नाही)
        return modelMapper.map(updatedSeller, SellerDto.class);
    }

    @Override
    public SellerDto updatePartialSeller(Long sellerId, Map<String, Object> updates) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Seller not found with id: " + sellerId));
        updates.forEach((field, value) -> {
            switch (field) {

                case "shopName":
                    seller.setShopName((String) value);
                    break;

                case "availableFishWasteKg":
                    seller.setAvailableFishWasteKg(((Number) value).intValue());
                    break;

                case "address":
                    seller.setAddress((String) value);
                    break;

                default:
                    throw new BadRequestException("Field '" + field + "' is not supported for update");
            }
        });
        Seller savedseller=sellerRepository.save(seller);
        return modelMapper.map(savedseller,SellerDto.class);
    }
}
