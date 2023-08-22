package com.eduardokp.criptoapp.security;

import com.eduardokp.criptoapp.dtos.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailService userDetailService;
    private final String START_TOKEN = "Bearer ";

    public JwtAuthFilter(JwtService jwtService, CustomUserDetailService userDetailService) {
        this.jwtService = jwtService;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        try {
            String authorization = request.getHeader("Authorization");

            if (authorization != null && authorization.startsWith(START_TOKEN)) {
                String token = authorization.split(" ")[1];
                String username = jwtService.getUsername(token);

                UserDetails user = userDetailService.loadUserByUsername(username);

                //creating an user to insert in spring security context
                UsernamePasswordAuthenticationToken userContext = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

                //configuring web authentication in spring
                userContext.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(userContext);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write(convertObjetToJson(new ResponseDTO<>("Inválid token")));
        }
    }

    public String convertObjetToJson(ResponseDTO<?> responseDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(responseDTO);
    }
}
