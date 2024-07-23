package br.com.andreisantos.gestao_vagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfilaCandidateResponseDTO {
    private String description;
    private String name;
    private String email;
    private UUID id;
    private String username;
}
