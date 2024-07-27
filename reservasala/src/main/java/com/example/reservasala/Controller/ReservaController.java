package com.example.reservasala.Controller;

import com.example.reservasala.DTO.ReservaRequestDTO;
import com.example.reservasala.DTO.ReservaResponseDTO;
import com.example.reservasala.Repository.ReservaRepository;
import com.example.reservasala.Rest.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaRepository reservaRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<ReservaResponseDTO>> getAllReservas() {
        try {
            List<ReservaResponseDTO> reservaList = reservaRepository.findAll().stream()
                    .map(ReservaResponseDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(reservaList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null); // Você pode retornar uma mensagem de erro aqui se desejar
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping()
    public ResponseEntity<String> fazerReserva(@RequestBody ReservaRequestDTO reservaRequestDTO) {
        try {
            // Cria um objeto Reserva com base nos dados do ReservaRequestDTO
            Reserva reserva = new Reserva();
            reserva.setIdPessoa(reservaRequestDTO.idPessoa());
            reserva.setIdSala(reservaRequestDTO.idSala());
            reserva.setDataInicio(reservaRequestDTO.dataInicio());
            reserva.setDataFim(reservaRequestDTO.dataFim());

            // Salva a reserva no banco de dados
            reservaRepository.save(reserva);

            // Retorna uma mensagem de sucesso
            return new ResponseEntity<>("Reserva feita com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            // Retorna uma mensagem de erro com o status HTTP 500 em caso de falha
            return new ResponseEntity<>("Erro ao fazer a reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReserva(@PathVariable Long id) {
        try {
            // Verifica se a reserva existe
            if (reservaRepository.existsById(id)) {
                // Remove a reserva
                reservaRepository.deleteById(id);
                return new ResponseEntity<>("Reserva deletada com sucesso!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Reserva não encontrada.", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // Retorna uma mensagem de erro com o status HTTP 500 em caso de falha
            return new ResponseEntity<>("Erro ao deletar a reserva: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

