package com.example.VulServer.AppUser.presentation;

import com.example.VulServer.AppUser.application.AppUserService;
import com.example.VulServer.AppUser.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    @GetMapping("/appusers")
    public ResponseEntity<List<AppUser>> findAll(){
        List<AppUser> res = appUserService.findAll();
        return ResponseEntity.ok(res);
    }
    @PostMapping("/appusers")
    public ResponseEntity<AppUser> save(@RequestBody AppUserReq appUserReq){
        AppUser res = appUserService.save(appUserReq);
        return ResponseEntity.ok(res);

    }
}
