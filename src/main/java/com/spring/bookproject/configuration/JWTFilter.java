package com.spring.bookproject.configuration;

import com.spring.bookproject.services.CustomUserDetailsService;
import com.spring.bookproject.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION_HEADER =  "Authorization";
    private static final String BEARER_PREFIX =  "Bearer ";

    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JWTFilter(JWTService jwtService,
                     CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        String token = null;
        String username = null;
        if (authorization == null || !authorization.startsWith(BEARER_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authorization.substring(BEARER_PREFIX.length());
        username =jwtService.extractUsername(token);

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = loadByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                setAuthentication(request, userDetails);
            }
        }
        filterChain.doFilter(request, response);

    }

    private static void setAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public UserDetails loadByUsername(String username) throws UsernameNotFoundException {
            return customUserDetailsService.loadUserByUsername(username);
    }
}
