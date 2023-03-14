package br.combakery.domain.repository;

import br.combakery.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
}
