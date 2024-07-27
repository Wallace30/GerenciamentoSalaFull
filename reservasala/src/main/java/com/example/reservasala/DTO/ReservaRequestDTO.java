package com.example.reservasala.DTO;

import java.util.Date;

public record ReservaRequestDTO(Long idPessoa, Long idSala, Date dataInicio, Date dataFim) {}
