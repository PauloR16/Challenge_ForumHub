package com.forum.forumapi.repository;

import com.forum.forumapi.domain.topico.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}