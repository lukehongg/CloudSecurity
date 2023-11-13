package com.example.VulServer.AppUser.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppUserReq {
    private String name;
    private int age;
}
