package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.andreisantos.gestao_vagas.modules.company.repositories.ICompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase implements IAuthCompanyUseCase{
    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(@NotNull AuthCompanyDTO authCompanyDTO) {
        var company = companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("Company not found")
        );

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if (!passwordMatches) {
            throw new AuthenticationException("Invalid password") {
            };
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create().withIssuer("javagas").withSubject(company.getId().toString())
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .sign(algorithm);
        return token;
    }
}
