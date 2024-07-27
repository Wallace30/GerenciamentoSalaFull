package com.example.reservasala.Controller;

import com.example.reservasala.DTO.PessoaResponseDTO;
import com.example.reservasala.DTO.SalaResponseDTO;
import com.example.reservasala.Repository.PessoaRepository;
import com.example.reservasala.Repository.SalaRepository;
import com.example.reservasala.Rest.CadastroPessoa;
import com.example.reservasala.Rest.CadastroSala;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cadastropessoa")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<?> getAllPedidos() {
        try {
            List<PessoaResponseDTO> pedidoList = repository.findAll().stream()
                    .map(PessoaResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(pedidoList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao buscar os pedidos: " + e.getMessage());
        }
    }
    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<String> registraSala(@RequestBody CadastroPessoa pedido) {
        try {
            CadastroPessoa saveSala = repository.save(pedido);
            return new ResponseEntity<>("Pessoa registrada com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao registrar a pessoa ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
