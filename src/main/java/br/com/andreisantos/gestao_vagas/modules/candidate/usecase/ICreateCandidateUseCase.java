package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface ICreateCandidateUseCase {
    public CandidateEntity execute(CandidateEntity candidateEntity) throws RuntimeException;
}
