package com.example.VulServer.AppUser.presentation;

import com.example.VulServer.AppUser.domain.AppUser;
import com.example.VulServer.common.DataEnDecryption;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class AppUserResponse {
    private String name;
    private int age;

    public static AppUserResponse from(AppUser appUser){
        return AppUserResponse.builder()
                .name(DataEnDecryption.decrypt(appUser.getName(),  "20231001100132022023100110013202", "handongstuxxpard"))
                .age(appUser.getAge())
                .build();
    }
}
