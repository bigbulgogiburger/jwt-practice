package com.pdh.jwtpractice.controller;


import com.pdh.jwtpractice.dto.LoginDto;
import com.pdh.jwtpractice.dto.TokenDto;
import com.pdh.jwtpractice.jwt.JwtFilter;
import com.pdh.jwtpractice.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER,"Bearer "+jwt);

        return new ResponseEntity<>(new TokenDto(jwt),httpHeaders, HttpStatus.OK);
    }
}
