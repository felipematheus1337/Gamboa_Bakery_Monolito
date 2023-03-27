package br.combakery.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Avaliacao implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int nota;

    public String descricao;

    @ManyToOne(optional = true)
    public Pedido pedido;
}
