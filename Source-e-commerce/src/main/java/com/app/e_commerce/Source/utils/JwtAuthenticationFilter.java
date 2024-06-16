package com.app.e_commerce.Source.utils;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.e_commerce.Source.exception.BusinessException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private final JwtDecoder jwtDecoder;

    @Autowired
    public JwtAuthenticationFilter(@Lazy JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        log.debug("JwtAuthenticationFilter.java.doFilterInternal [start]...");

        try {

            String token = request.getHeader("Authorization");
            log.debug("token [{}]", token);
            
            if (token != null && token.startsWith("Bearer")) {
                
                token = token.replaceFirst("Bearer" + " ", StringUtils.EMPTY);
                Jwt jwt = jwtDecoder.decode(token);

                // TODO Logic for access end point

                JwtAuthenticationToken authentication = new JwtAuthenticationToken(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }else{
                
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                SecurityContextHolder.clearContext();
            }

            
        } catch (JwtException e) {
            log.error("Got Exception JwtException...", e);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            SecurityContextHolder.clearContext();
            throw new BusinessException("0001ERR");
        } catch (BadCredentialsException ex) {
			throw new BusinessException("0001ERR");

		}catch (Exception e) {
            log.error("Got Exception Exception...", e);
            SecurityContextHolder.clearContext();

        }

        filterChain.doFilter(request, response);

        log.debug("JwtAuthenticationFilter.java.doFilterInternal [end]...");
    }
    
}
