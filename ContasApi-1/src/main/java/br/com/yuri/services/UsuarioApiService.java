package br.com.yuri.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.yuri.dtos.UsuarioGetDto;

@Service
public class UsuarioApiService {

    @Autowired
    private WebClient webClient;

    public UsuarioGetDto buscarUsuarioPorId(String usuarioId) {
        try {
            System.out.println("🔍 Buscando usuário: " + usuarioId);
            System.out.println("📍 Chamando: http://localhost:8082/api/usuarios/" + usuarioId);
            
            UsuarioGetDto response = webClient
                    .get()
                    .uri("/api/usuarios/{id}", usuarioId)
                    .retrieve()
                    .bodyToMono(UsuarioGetDto.class)
                    .block();
            
            System.out.println("✅ Usuário encontrado: " + response);
            return response;
            
        } catch (Exception e) {
            System.err.println("❌ ERRO AO BUSCAR USUÁRIO: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}