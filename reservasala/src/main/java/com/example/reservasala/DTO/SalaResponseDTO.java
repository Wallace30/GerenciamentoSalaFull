package com.example.reservasala.DTO;

import com.example.reservasala.Rest.CadastroSala;

public record SalaResponseDTO(Long id, String nome, int capacidade) {

    public SalaResponseDTO(CadastroSala sala)
    {
        this(sala.getId(),sala.getNome(),sala.getCapacidade());
    }
}
