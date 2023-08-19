package ru.davydenko.springcourse.securityapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.davydenko.springcourse.securityapp.services.MemberDetailService;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final MemberDetailService memberDetailService;

    @Autowired
    public AuthProviderImpl(MemberDetailService memberDetailService) {
        this.memberDetailService = memberDetailService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails memberDetails = memberDetailService.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();
        if (!password.equals(memberDetails.getPassword()))
            throw  new BadCredentialsException("Incorrect password");

        return new UsernamePasswordAuthenticationToken(memberDetails, password,
                Collections.emptyList());
        }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
