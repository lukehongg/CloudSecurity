package com.example.VulServer.AppUser.presentation;

import com.example.VulServer.AppUser.application.AppUserService;
import com.example.VulServer.AppUser.domain.AppUser;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api")
public class AppUserController {

    private final Bucket bucket;

    @Autowired
    private AppUserService appUserService;


    public AppUserController() {
        // 충전 간격을 10초로 지정하며, 한 번 충전할 때마다 2개의 토큰을 충전한다.
        Refill refill = Refill.intervally(2, Duration.ofSeconds(10));

        // Bucket의 총 크기는 3
        Bandwidth limit = Bandwidth.classic(3, refill);

        // 총 크기는 3이며 10초마다 2개의 토큰을 충전하는 Bucket
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @GetMapping("/appusers")
    public ResponseEntity<List<AppUserResponse>> findAll(){
        if (bucket.tryConsume(1)) {
            List<AppUserResponse> res = appUserService.findAll();
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();

    }
    @PostMapping("/appusers")
    public ResponseEntity<AppUser> save(@RequestBody AppUserReq appUserReq){
        if (bucket.tryConsume(1)) {
            AppUser res = appUserService.save(appUserReq);
            return ResponseEntity.ok(res);
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }
}
