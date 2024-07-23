package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.andreisantos.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public interface IAuthCompanyUseCase {
    public AuthCompanyResponseDTO execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException;
}
