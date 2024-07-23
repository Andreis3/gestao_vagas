package br.com.andreisantos.gestao_vagas.modules.candidate.controllers;

import br.com.andreisantos.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.andreisantos.gestao_vagas.modules.candidate.usecase.IAuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {
    @Autowired
    private IAuthCandidateUseCase authCandidateUseCase;

    @PostMapping("/candidate")
    public ResponseEntity<Object> auth(@RequestBody AuthCandidateRequestDTO authCandidateRequestDTO) {
        try {
            var authCandidateResponse = this.authCandidateUseCase.execute(authCandidateRequestDTO);
            return ResponseEntity.status(HttpStatus.OK).body(authCandidateResponse);
        }catch (Exception e) {
            Map<String, String> body = Map.of("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body);
        }

    }
}

