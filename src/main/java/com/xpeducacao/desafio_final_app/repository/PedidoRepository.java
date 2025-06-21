package com.xpeducacao.desafio_final_app.repository;

import com.xpeducacao.desafio_final_app.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByClienteNomeContainingIgnoreCase(String nome);
}