package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.exceptions.UserFoundException;
import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.andreisantos.gestao_vagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ICreateCompanyUseCase{
    public CompanyEntity execute(CompanyEntity companyEntity) throws RuntimeException;
}