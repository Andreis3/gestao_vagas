package br.com.andreisantos.gestao_vagas.security;

import br.com.andreisantos.gestao_vagas.providers.JWTCandidateProvider;
import br.com.andreisantos.gestao_vagas.providers.JWTProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {
    @Autowired
    private JWTCandidateProvider jwtProvider;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        if(request.getRequestURI().contains("/auth/candidate")){
            if (header != null) {
                var token = this.jwtProvider.validateToken(header);
                if (token == null) {
                    this.httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                    return;
                }
                request.setAttribute("candidate_id", token.getSubject());
                var roles = token.getClaim("roles").asList(String.class);

                var grants = roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString())).toList();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(token.getSubject(), null, grants);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
