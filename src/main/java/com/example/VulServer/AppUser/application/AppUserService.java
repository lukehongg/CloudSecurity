package com.example.VulServer.AppUser.application;

import com.example.VulServer.AppUser.domain.AppUser;
import com.example.VulServer.AppUser.domain.AppUserRepo;
import com.example.VulServer.AppUser.presentation.AppUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppUserService {
    @Autowired
    private AppUserRepo appUserRepo;
//    private static MongoTemplate mongoTemplate;
    @Transactional(readOnly = true)
    public List<AppUser> findAll() {
        List<AppUser> ret = appUserRepo.findAll();
        return ret;
    }

    @Transactional
    public AppUser save(AppUserReq appUserReq) {
        AppUser ret = appUserRepo.save(AppUser.from(appUserReq));
        return ret;
    }
}
