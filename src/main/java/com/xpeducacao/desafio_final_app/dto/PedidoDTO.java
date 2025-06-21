package com.xpeducacao.desafio_final_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {
    private Long id;
    private ClienteDTO cliente;
    private List<PedidoItemDTO> itens;
    private LocalDateTime dataCriacao;
    private String status;
    private Double valorTotal;
}

