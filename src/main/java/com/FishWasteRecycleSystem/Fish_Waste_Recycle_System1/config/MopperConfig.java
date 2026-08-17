package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MopperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}