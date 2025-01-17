package br.com.andreisantos.gestao_vagas.modules.company.repositories;

import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ICompanyRepository extends JpaRepository<CompanyEntity, UUID>{
    Optional<CompanyEntity> findByUsernameOrWebsite(String username, String website);
    Optional<CompanyEntity> findByUsername(String username);
}
