package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.andreisantos.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.andreisantos.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase implements IAuthCandidateUseCase{

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private ICandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) {
        var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("username or password incorrect");
                });

        if (!this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword())) {
            throw new AuthenticationException("username or password incorrect") {
            };
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas").withSubject(candidate.getId().toString())
                .withClaim("role", Arrays.asList("candidate"))
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);

        var authCandidateResponse = AuthCandidateResponseDTO.builder()
                .access_token(token)
                .build();

        return authCandidateResponse;
    }
}
