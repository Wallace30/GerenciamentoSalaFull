package com.example.reservasala.Repository;

import com.example.reservasala.Rest.CadastroPessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<CadastroPessoa,Long> {
}
