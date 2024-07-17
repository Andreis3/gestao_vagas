package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;

public interface ICreateCompanyUseCase{
    public CompanyEntity execute(CompanyEntity companyEntity) throws RuntimeException;
}