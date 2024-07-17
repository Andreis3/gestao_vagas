package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.andreisantos.gestao_vagas.modules.company.repositories.ICompanyRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public interface IAuthCompanyUseCase {
    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException;
}
