package com.example.VulServer.AppUser.presentation;

import com.example.VulServer.AppUser.application.AppUserService;
import com.example.VulServer.AppUser.domain.AppUser;

import com.example.VulServer.common.BucketService;
import io.github.bucket4j.Bucket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppUserController {

    private final BucketService bucketService;
    private final AppUserService appUserService;


    @GetMapping("/appusers")
    public ResponseEntity<List<AppUserResponse>> findAll(HttpServletRequest request){
        Bucket bucket = bucketService.resolveBucket(request);
//        System.out.println("접근 IP = {" + request.getRemoteAddr() + "}");
        if (bucket.tryConsume(1)) {
            List<AppUserResponse> res = appUserService.findAll();
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }
    @PostMapping("/appusers")
    public ResponseEntity<AppUser> save(@RequestBody AppUserReq appUserReq, HttpServletRequest request){
        Bucket bucket = bucketService.resolveBucket(request);
//        System.out.println("접근 IP = {" + request.getRemoteAddr() + "}");
        if (bucket.tryConsume(1)) {
            AppUser res = appUserService.save(appUserReq);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
