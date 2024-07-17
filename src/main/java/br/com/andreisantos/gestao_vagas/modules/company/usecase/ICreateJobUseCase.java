package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.andreisantos.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.stereotype.Service;

@Service
public interface ICreateJobUseCase {
    public JobEntity execute(JobEntity jobEntity) throws RuntimeException;
}