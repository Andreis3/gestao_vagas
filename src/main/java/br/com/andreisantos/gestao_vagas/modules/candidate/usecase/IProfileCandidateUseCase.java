package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.modules.candidate.dto.ProfilaCandidateResponseDTO;

import java.util.UUID;

public interface IProfileCandidateUseCase {
    ProfilaCandidateResponseDTO execute(UUID candidateId);
}
