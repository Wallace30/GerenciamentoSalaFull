package com.example.reservasala.Rest;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Reserva {

    @Id
    @GeneratedValue
    private Long id;
    private Long idPessoa;
    private Long idSala; // Mudamos de int para Long para ser compatível com a chave primária de CadastroSala
    private Date dataInicio;
    private Date dataFim;

}
