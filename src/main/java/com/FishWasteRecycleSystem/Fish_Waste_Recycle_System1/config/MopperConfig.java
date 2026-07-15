package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import  org.modelmapper.ModelMapper;
@Configuration
public class MopperConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}

