package com.github.backproject.web.controller.auth;

import com.github.backproject.service.security.AuthService;
import com.github.backproject.web.dto.auth.Login;
import com.github.backproject.web.dto.auth.SignUp;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign")
    public String signUp(@RequestBody SignUp signUpRequest) {
        boolean isSuccess = authService.signUp(signUpRequest);
        return isSuccess ? "회원가입을 성공했습니다." : "회원가입 실패하였습니다.";
    }

    @PostMapping("/login")
    public String Login(@RequestBody Login loginRequest, HttpServletResponse response) {
        String token = authService.login(loginRequest);
        response.setHeader("X-AUTH-TOKEN",token);
        return "로그인 되었습니다";
    }
}
