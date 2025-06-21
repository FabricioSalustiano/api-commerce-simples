package com.xpeducacao.desafio_final_app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PedidoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Long clienteId;

    @NotEmpty(message = "A lista de itens não pode estar vazia")
    @Valid
    private List<PedidoItemRequestDTO> itens;
}
