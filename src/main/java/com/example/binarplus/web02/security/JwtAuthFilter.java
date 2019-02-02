package com.example.binarplus.web02.security;

import com.example.binarplus.web02.domain.Pengguna;
import com.example.binarplus.web02.service.CustomUSerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

import static com.example.binarplus.web02.security.SecurityConstant.HEADER_STRING;
import static com.example.binarplus.web02.security.SecurityConstant.TOKEN_PREFIX;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUSerDetailsService customUSerDetailsService;

    private String getJWTFromRequest(HttpServletResponse httpServletResponse){
        String bearerToken = httpServletResponse.getHeader(HEADER_STRING);

        if (StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(6, bearerToken.length());
        }
        return null;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt = getJWTFromRequest (httpServletResponse);
            if(StringUtils.hasText(jwt)) jwtTokenProvider.validateToken(jwt);{

                Long userId = jwtTokenProvider.getUseridfromJWT(jwt);
                Pengguna userdetails = customUSerDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userdetails, null, Collections.emptyList());
                        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception ex){
            logger.error("Couldnt set user auth in securoty context", ex);

        }filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
