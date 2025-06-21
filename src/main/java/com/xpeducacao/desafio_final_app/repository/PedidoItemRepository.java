package com.xpeducacao.desafio_final_app.repository;

import com.xpeducacao.desafio_final_app.model.PedidoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long> {

    List<PedidoItem> findByPedidoId(Long pedidoId);

    Optional<PedidoItem> findByIdAndPedidoId(Long id, Long pedidoId);
}
