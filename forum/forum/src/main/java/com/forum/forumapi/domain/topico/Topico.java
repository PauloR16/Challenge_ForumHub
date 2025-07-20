package com.forum.forumapi.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusTopico status;
    private String autor;
    private String curso;

    public Topico(DadosCadastroTopico dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTopico.ABERTO; // Status inicial
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.autor() != null) {
            this.autor = dados.autor();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }
        if (dados.status() != null) {
            this.status = dados.status();
        }
    }

    public void excluir() {
        this.status = StatusTopico.FECHADO; // Ou você pode realmente deletar
    }
}