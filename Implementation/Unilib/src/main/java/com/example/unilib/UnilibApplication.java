package com.example.unilib;

import com.example.unilib.repository.ArquivoLivroRepository;
import com.example.unilib.service.LivroService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnilibApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnilibApplication.class, args);
    }

    @Bean
    public LivroService livroService() {
        ArquivoLivroRepository livroRepository = new ArquivoLivroRepository();

        return new LivroService(livroRepository);
    }
}
