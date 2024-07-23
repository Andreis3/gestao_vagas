package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.modules.candidate.dto.ProfilaCandidateResponseDTO;
import br.com.andreisantos.gestao_vagas.modules.candidate.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase implements IProfileCandidateUseCase {
    @Autowired
    private ICandidateRepository candidateRepository;
    public ProfilaCandidateResponseDTO execute(UUID candidateId) {
        var candidate = this.candidateRepository.findById(candidateId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("candidate not found");
                });
        var candidateDTO = ProfilaCandidateResponseDTO.builder()
                .description(candidate.getDescription())
                .email(candidate.getEmail())
                .id(candidate.getId())
                .name(candidate.getName())
                .username(candidate.getUsername())
                .build();
        return candidateDTO;
    }

}
