package br.com.andreisantos.gestao_vagas.modules.candidate.controllers;

import br.com.andreisantos.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.andreisantos.gestao_vagas.modules.candidate.usecase.ICreateCandidateUseCase;
import br.com.andreisantos.gestao_vagas.modules.candidate.usecase.IProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    private ICreateCandidateUseCase createCandidateUseCase;
    @Autowired
    private IProfileCandidateUseCase profileCandidateUseCase;

    @PostMapping("")
    public ResponseEntity<Object> createCandidate(@Valid @RequestBody CandidateEntity candidateEntity) {
        try {
            var result = this.createCandidateUseCase.execute(candidateEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            Map<String, String> body = Map.of("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('candidate')")
    public ResponseEntity<Object> getCandidate(HttpServletRequest request) {
        var candidateId = request.getAttribute("candidate_id");
        try {
            var result = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            Map<String, String> body = Map.of("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
        }
    }
}
