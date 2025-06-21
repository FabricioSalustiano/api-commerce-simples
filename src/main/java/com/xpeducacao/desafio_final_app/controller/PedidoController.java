package com.xpeducacao.desafio_final_app.controller;

import com.xpeducacao.desafio_final_app.dto.PedidoDTO;
import com.xpeducacao.desafio_final_app.dto.PedidoItemRequestDTO;
import com.xpeducacao.desafio_final_app.dto.PedidoRequestDTO;
import com.xpeducacao.desafio_final_app.model.Cliente;
import com.xpeducacao.desafio_final_app.model.Pedido;
import com.xpeducacao.desafio_final_app.model.PedidoItem;
import com.xpeducacao.desafio_final_app.model.Produto;
import com.xpeducacao.desafio_final_app.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@Tag(name = "Pedidos", description = "Operações relacionadas ao domínio Pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Listar todos os pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido encontrado")
    })
    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarTodos() {
        List<Pedido> pedidos = pedidoService.listarTodos();

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PedidoDTO> pedidosDTO = pedidos.stream()
                .map(pedidoService::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pedidosDTO);
    }

    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(
            @Parameter(description = "ID do pedido") @PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(pedido -> ResponseEntity.ok(pedidoService.toDTO(pedido)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Buscar pedidos por nome do cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido encontrado com esse nome de cliente")
    })
    @GetMapping("/buscar")
    public ResponseEntity<List<PedidoDTO>> buscarPorClienteNome(
            @Parameter(description = "Nome do cliente") @RequestParam String nome) {
        List<Pedido> pedidos = pedidoService.buscarPorClienteNome(nome);

        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PedidoDTO> dtos = pedidos.stream()
                .map(pedidoService::toDTO)
                .toList();

        return ResponseEntity.ok(dtos);
    }

    @Operation(summary = "Criar um novo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    @PostMapping
    public ResponseEntity<Pedido> salvar(
            @Valid @RequestBody PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setCliente(Cliente.builder().id(dto.getClienteId()).build());

        for (PedidoItemRequestDTO itemDTO : dto.getItens()) {
            PedidoItem item = new PedidoItem();
            item.setProduto(new Produto(itemDTO.getProdutoId(), null, null, null));
            item.setQuantidade(itemDTO.getQuantidade());
            pedido.adicionarItem(item);
        }

        Pedido salvo = pedidoService.salvar(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @Operation(summary = "Atualizar um pedido existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> atualizar(
            @Parameter(description = "ID do pedido") @PathVariable Long id,
            @RequestBody Pedido pedidoAtualizado) {
        return pedidoService.atualizar(id, pedidoAtualizado)
                .map(pedidoService::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do pedido") @PathVariable Long id) {
        boolean deletado = pedidoService.deletar(id);

        if (deletado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Contar pedidos cadastrados")
    @ApiResponse(responseCode = "200", description = "Contagem realizada com sucesso")
    @GetMapping("/contar")
    public ResponseEntity<Long> contar() {
        return ResponseEntity.ok(pedidoService.contarPedidos());
    }
}