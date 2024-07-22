package br.com.andreisantos.gestao_vagas.modules.company.controller;

import br.com.andreisantos.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.andreisantos.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.andreisantos.gestao_vagas.modules.company.entities.JobEntity;
import br.com.andreisantos.gestao_vagas.modules.company.usecase.ICreateCompanyUseCase;
import br.com.andreisantos.gestao_vagas.modules.company.usecase.ICreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private ICreateJobUseCase createJobUseCase;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO job, HttpServletRequest request) {
        try {

            var companyId = request.getAttribute("company_id");
            var jobEntity = JobEntity.builder()
                    .description(job.getDescription())
                    .benefits(job.getBenefits())
                    .level(job.getLevel())
                    .companyId(UUID.fromString(companyId.toString()))
                    .build();
            var result = this.createJobUseCase.execute(jobEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            Map<String, String> body = Map.of("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }

    }
}
