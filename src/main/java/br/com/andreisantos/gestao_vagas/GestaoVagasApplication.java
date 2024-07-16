package br.com.andreisantos.gestao_vagas;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class GestaoVagasApplication {

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure().filename(".env").ignoreIfMissing().systemProperties().load();

        SpringApplication.run(GestaoVagasApplication.class, args);

    }

}
