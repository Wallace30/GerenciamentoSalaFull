package com.example.reservasala.DTO;

import com.example.reservasala.Rest.CadastroPessoa;

public record PessoaResponseDTO(Long id, String nome, int idade, Long cpf) {

    public PessoaResponseDTO(CadastroPessoa pessoa)
    {
        this(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf());
    }
}
