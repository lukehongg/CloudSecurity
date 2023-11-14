package com.example.VulServer.AppUser.domain;

import com.example.VulServer.AppUser.presentation.AppUserReq;
import com.example.VulServer.common.DataEnDecryption;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "test_AppUser") // 실제 몽고 DB 컬렉션 이름
@Getter
@Setter
@Builder
public class AppUser {
    @Id
    private String id;
    private String name;
    private int age;

    public static AppUser from(AppUserReq request, String EncIv, String EncSecretKey){
        return AppUser.builder()
                .name(DataEnDecryption.encrypt(request.getName(), EncSecretKey, EncIv))
                .age(request.getAge())
                .build();
    }
}
