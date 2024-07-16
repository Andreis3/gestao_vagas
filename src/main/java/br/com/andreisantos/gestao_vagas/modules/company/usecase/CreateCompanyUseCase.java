package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.exceptions.UserFoundException;
import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.andreisantos.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase implements ICreateCompanyUseCase{
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
                .ifPresent(company -> {
                    throw new UserFoundException("Company already exists");
                });
        return this.companyRepository.save(companyEntity);
    }
}
