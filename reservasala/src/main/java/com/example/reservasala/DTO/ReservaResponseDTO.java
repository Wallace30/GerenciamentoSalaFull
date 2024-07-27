package com.example.reservasala.DTO;

import com.example.reservasala.Rest.Reserva;

import java.util.Date;

public record ReservaResponseDTO(Long id, Long idPessoa, Long idSala, Date dataInicio, Date dataFim) {

    public ReservaResponseDTO(Reserva reserva) {
        this(reserva.getId(), reserva.getIdPessoa(), reserva.getIdSala(), reserva.getDataInicio(), reserva.getDataFim());
    }
}

