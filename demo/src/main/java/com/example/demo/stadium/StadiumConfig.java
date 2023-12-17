package com.example.demo.stadium;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.List;

@Configuration
public class StadiumConfig {

    @Bean
    CommandLineRunner clr(StadiumSave stadiumSave){

        return args -> {
          Stadium chelsea=new Stadium(1L,"Stamford Bridge",40341L,1L);
          Stadium ars=new Stadium(2L,"Emirates Stadium",60704L,2L);

          chelsea.setModificationDate(ZonedDateTime.now());
          ars.setModificationDate(ZonedDateTime.now());
            stadiumSave.saveAll(List.of(chelsea,ars));
        };

    }
}
