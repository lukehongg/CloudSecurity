package com.example.VulServer.AppUser.application;

import com.example.VulServer.AppUser.domain.AppUser;
import com.example.VulServer.AppUser.domain.AppUserRepo;
import com.example.VulServer.AppUser.presentation.AppUserReq;
import com.example.VulServer.AppUser.presentation.AppUserResponse;
import com.example.VulServer.common.DataEnDecryption;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppUserService {

//    @Value("${crypto.EncIv}")
    private static String EncIv = "handongstuxxpard";

//    @Value("${crypto.EncSecretKey}")
    private static String EncSecretKey = "20231001100132022023100110013202";

    @Autowired
    private AppUserRepo appUserRepo;

    @Transactional(readOnly = true)
    public List<AppUserResponse> findAll() {
        List<AppUserResponse> ret = appUserRepo.findAll().stream().map(AppUserResponse::from).collect(Collectors.toList());
        return ret;
    }

    @Transactional
    public AppUser save(AppUserReq appUserReq) {
        AppUser ret = appUserRepo.save(AppUser.from(appUserReq, EncIv, EncSecretKey));
        return ret;
    }
}
