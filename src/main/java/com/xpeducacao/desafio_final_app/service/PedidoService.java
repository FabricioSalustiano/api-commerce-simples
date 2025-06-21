package com.xpeducacao.desafio_final_app.service;

import com.xpeducacao.desafio_final_app.dto.ClienteDTO;
import com.xpeducacao.desafio_final_app.dto.PedidoDTO;
import com.xpeducacao.desafio_final_app.dto.PedidoItemDTO;
import com.xpeducacao.desafio_final_app.dto.ProdutoDTO;
import com.xpeducacao.desafio_final_app.model.Cliente;
import com.xpeducacao.desafio_final_app.model.Pedido;
import com.xpeducacao.desafio_final_app.model.Produto;
import com.xpeducacao.desafio_final_app.repository.ClienteRepository;
import com.xpeducacao.desafio_final_app.repository.PedidoRepository;
import com.xpeducacao.desafio_final_app.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(
            PedidoRepository pedidoRepository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository
    ) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorClienteNome(String nome) {
        return pedidoRepository.findByClienteNomeContainingIgnoreCase(nome);
    }

    public Pedido salvar(Pedido pedido) {
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        pedido.setDataCriacao(LocalDateTime.now());

        double total = 0.0;
        for (var item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            item.setProduto(produto);
            item.setPrecoUnitario(produto.getPreco());

            double valorItem = produto.getPreco() * item.getQuantidade();
            total += valorItem;

            item.setPedido(pedido);
        }

        pedido.setValorTotal(total);

        if (pedido.getStatus() == null)
            pedido.setStatus("CRIADO");

        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> atualizar(Long id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(pedidoExistente -> {
            pedidoExistente.setCliente(pedidoAtualizado.getCliente());
            pedidoExistente.setStatus(pedidoAtualizado.getStatus());
            pedidoExistente.setDataCriacao(pedidoAtualizado.getDataCriacao());
            pedidoExistente.getItens().clear();

            if (pedidoAtualizado.getItens() != null) {
                pedidoAtualizado.getItens().forEach(item -> {
                    item.setPedido(pedidoExistente);
                    pedidoExistente.getItens().add(item);
                });
            }

            double valorTotal = pedidoExistente.getItens().stream()
                    .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                    .sum();
            pedidoExistente.setValorTotal(valorTotal);

            return pedidoRepository.save(pedidoExistente);
        });
    }

    public boolean deletar(Long id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public long contarPedidos() {
        return pedidoRepository.count();
    }

    public PedidoDTO toDTO(Pedido pedido) {
        Cliente cliente = pedido.getCliente();
        ClienteDTO clienteDTO = new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());

        List<PedidoItemDTO> itensDTO = pedido.getItens().stream().map(item -> {
            Produto p = item.getProduto();
            ProdutoDTO produtoDTO = new ProdutoDTO(p.getId(), p.getNome(), p.getDescricao(), p.getPreco());
            return new PedidoItemDTO(
                    item.getId(),
                    produtoDTO,
                    item.getQuantidade(),
                    item.getPrecoUnitario(),
                    item.getPrecoUnitario() * item.getQuantidade()
            );
        }).collect(Collectors.toList());

        return new PedidoDTO(
                pedido.getId(),
                clienteDTO,
                itensDTO,
                pedido.getDataCriacao(),
                pedido.getStatus(),
                pedido.getValorTotal()
        );
    }
}
