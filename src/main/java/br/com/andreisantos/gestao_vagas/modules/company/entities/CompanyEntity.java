package br.com.andreisantos.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "companies")
@EntityListeners(AuditingEntityListener.class)
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @Pattern(regexp = "\\S+", message = "Username should not be empty")
    private String username;
    @Size(min = 8, max = 150, message = "Password should have between 8 and 16 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$", message = "Password should have at least one lowercase letter, one uppercase letter and one number")
    private String password;
    private String description;
    private String website;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @PastOrPresent
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @PastOrPresent
    private LocalDateTime updatedAt;

}
