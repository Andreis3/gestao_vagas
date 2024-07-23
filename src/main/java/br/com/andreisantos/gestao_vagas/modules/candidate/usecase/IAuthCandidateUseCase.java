package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.andreisantos.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

public interface IAuthCandidateUseCase {
    AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO);
}
