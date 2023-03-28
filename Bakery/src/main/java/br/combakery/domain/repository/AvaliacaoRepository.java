package br.combakery.domain.repository;


import br.combakery.api.dto.Media;
import br.combakery.domain.entity.Avaliacao;
import jakarta.persistence.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {


    Optional<List<Avaliacao>> findByPedidoId(Long pedidoId);

    @Query("SELECT NEW br.combakery.api.dto.Media(a.pedido.id as pedidoId, AVG(a.nota) as media, COUNT(a) as totalAvaliacoes) " +
            "FROM Avaliacao a " +
            "WHERE a.pedido.id = :pedidoId " +
            "GROUP BY a.pedido.id")
    Media getMedia(Long pedidoId);

}
