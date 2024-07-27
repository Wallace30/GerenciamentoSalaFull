package com.example.reservasala.Repository;

import com.example.reservasala.Rest.CadastroSala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository extends JpaRepository<CadastroSala, Long> {
}
