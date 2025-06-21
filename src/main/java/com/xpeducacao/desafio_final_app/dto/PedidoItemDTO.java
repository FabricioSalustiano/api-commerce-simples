package com.xpeducacao.desafio_final_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemDTO {
    private Long id;
    private ProdutoDTO produto;
    private Integer quantidade;
    private Double precoUnitario;
    private Double valorTotal;
}

