package com.example.VulServer.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class UserAgentInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String userAgent = request.getHeader("User-Agent");

        // 신뢰할 수 있는 User Agent 값인지 확인하는 로직을 구현
        if (isValidUserAgent(userAgent)) {
            return true; // 허용
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 거부 상태코드 설정
            return false; // 거부
        }
    }

    private boolean isValidUserAgent(String userAgent) {
        String allowedPattern = ".*Chrome/.*|.*Safari/.*|.*Firefox/.*|.*Macintosh\\s+(Intel|PPC|Mac OS X [0-9_]+(\\s+\\w+)?)\\s*|.*Windows NT [0-9]+\\.[0-9]+|.*Android.*|.*iPhone.*";
//        return userAgent.matches(allowedPattern);
        return true;
    }
//    private boolean isValidUserAgent(String userAgent) {
//        String allowedPattern = ".*Macintosh\\s+(Intel|PPC|Mac OS X [0-9_]+(\\s+\\w+)?)\\s*|.*Windows NT [0-9]+\\.[0-9]+|.*Android.*|.*iPhone.*";
//        return userAgent.matches(allowedPattern);
//    }


}
