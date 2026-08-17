package com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.FishWasteRecycleSystem.Fish_Waste_Recycle_System1.service.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // 1. Authorization Header
        String authHeader = request.getHeader("Authorization");

        // 2. Header null किंवा Bearer नसल्यास पुढे जाऊ
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Token काढ
        String token = authHeader.substring(7);

        // 4. Email काढ
        String email = jwtService.extractEmail(token);

        // 5. आधी authenticate नसेल तर
        if (email != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // 6. DB मधून user load कर
            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(email);

            // 7. Token validate कर
            if (jwtService.isTokenValid(token, userDetails)) {

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request));

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            }
        }

        // 8. पुढच्या filter कडे request पाठव
        filterChain.doFilter(request, response);
    }
}