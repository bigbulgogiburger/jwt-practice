package com.pdh.jwtpractice.util;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {
    public static Optional<String> getCurrentUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication== null){
            log.debug("security context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();

        }

        return Optional.ofNullable(username);
    }


}
