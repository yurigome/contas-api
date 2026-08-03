package br.com.yuri.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yuri.dtos.ContaGetDto;
import br.com.yuri.dtos.ContaPostDto;
import br.com.yuri.dtos.ContaPutDto;
import br.com.yuri.dtos.UsuarioGetDto;
import br.com.yuri.entities.Conta;
import br.com.yuri.repositories.ContaRepository;
import br.com.yuri.services.UsuarioApiService;

@RestController
@RequestMapping(value = "/api/conta")
@CrossOrigin(origins = "http://localhost:4200")  // ✅ ADICIONADO
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UsuarioApiService usuarioApiService;

    @PostMapping
    public ContaGetDto post(@RequestBody ContaPostDto contaRecebida) {

        Conta conta = mapper.map(contaRecebida, Conta.class);
        conta.setIdConta(UUID.randomUUID());

        // 🔥 VALIDA O USUÁRIO NA API DE USUÁRIOS
        UsuarioGetDto usuarioDto = usuarioApiService.buscarUsuarioPorId(
                contaRecebida.getUsuarioId().toString());

        if (usuarioDto == null) {
            throw new RuntimeException("Usuário não encontrado na API de Usuários");
        }

        // ✅ SÓ GUARDA O ID
        conta.setUsuarioId(contaRecebida.getUsuarioId());
        contaRepository.save(conta);

        // 🔥 MONTA A RESPOSTA COM O USUÁRIO
        ContaGetDto response = mapper.map(conta, ContaGetDto.class);
        response.setUsuario(usuarioDto);

        return response;
    }

    @PutMapping
    public ContaGetDto atualizar(@RequestBody ContaPutDto contaAtualizada) {

        Conta conta = contaRepository.findById(contaAtualizada.getIdConta())
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.setNome(contaAtualizada.getNome());
        conta.setValor(contaAtualizada.getValor());
        conta.setData(contaAtualizada.getData());
        conta.setDescricao(contaAtualizada.getDescricao());
        conta.setTipo(contaAtualizada.getTipo());

        contaRepository.save(conta);

        // 🔥 BUSCA O USUÁRIO NA API DE USUÁRIOS
        UsuarioGetDto usuarioDto = usuarioApiService.buscarUsuarioPorId(
                conta.getUsuarioId().toString());

        ContaGetDto response = mapper.map(conta, ContaGetDto.class);
        response.setUsuario(usuarioDto);

        return response;
    }

    @DeleteMapping("{idConta}")
    public ContaGetDto delete(@PathVariable("idConta") UUID idConta) {

        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        // 🔥 BUSCA O USUÁRIO NA API DE USUÁRIOS
        UsuarioGetDto usuarioDto = usuarioApiService.buscarUsuarioPorId(
                conta.getUsuarioId().toString());

        contaRepository.delete(conta);

        ContaGetDto response = mapper.map(conta, ContaGetDto.class);
        response.setUsuario(usuarioDto);

        return response;
    }

    @GetMapping
    public List<ContaGetDto> get() {

        List<Conta> contas = contaRepository.findAll();

        List<ContaGetDto> result = contas.stream().map(conta -> {
            ContaGetDto dto = mapper.map(conta, ContaGetDto.class);

            // 🔥 BUSCA O USUÁRIO NA API DE USUÁRIOS
            UsuarioGetDto usuarioDto = usuarioApiService.buscarUsuarioPorId(
                    conta.getUsuarioId().toString());

            dto.setUsuario(usuarioDto);
            return dto;
        }).collect(Collectors.toList());

        return result;
    }

    @GetMapping("{idConta}")
    public ContaGetDto getById(@PathVariable("idConta") UUID idConta) {

        Conta conta = contaRepository.findById(idConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        ContaGetDto dto = mapper.map(conta, ContaGetDto.class);

        // 🔥 BUSCA O USUÁRIO NA API DE USUÁRIOS
        UsuarioGetDto usuarioDto = usuarioApiService.buscarUsuarioPorId(
                conta.getUsuarioId().toString());

        dto.setUsuario(usuarioDto);

        return dto;
    }
}