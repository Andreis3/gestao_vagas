package br.com.andreisantos.gestao_vagas.modules.company.usecase;

import br.com.andreisantos.gestao_vagas.exceptions.UserFoundException;
import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.andreisantos.gestao_vagas.modules.company.repositories.ICompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCompanyUseCase implements ICreateCompanyUseCase{
    @Autowired
    private ICompanyRepository companyRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository.findByUsernameOrWebsite(companyEntity.getUsername(), companyEntity.getWebsite())
                .ifPresent(company -> {
                    throw new UserFoundException("Company already exists");
                });
        var password = this.passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(password);
        return this.companyRepository.save(companyEntity);
    }
}
