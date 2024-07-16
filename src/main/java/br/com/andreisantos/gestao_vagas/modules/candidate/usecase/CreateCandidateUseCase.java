package br.com.andreisantos.gestao_vagas.modules.candidate.usecase;

import br.com.andreisantos.gestao_vagas.exceptions.UserFoundException;
import br.com.andreisantos.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.andreisantos.gestao_vagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase implements ICreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity) {
        this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent(candidate -> {
                    throw new UserFoundException("Candidate already exists");
                });
        return this.candidateRepository.save(candidateEntity);
    }
}
