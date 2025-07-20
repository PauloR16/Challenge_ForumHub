package com.forum.forumapi.controller;

import com.forum.forumapi.domain.ValidacaoException;
import com.forum.forumapi.domain.topico.*;
import com.forum.forumapi.repository.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity criarTopico(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            throw new ValidacaoException("Já existe um tópico com o mesmo título e mensagem.");
        }
        var topico = new Topico(dados);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listarTopicos(@PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharTopico(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new ValidacaoException("ID do tópico inválido ou não encontrado.");
        }
        var topico = topicoOptional.get();
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarTopico(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        if (dados.id() != null && !dados.id().equals(id)) {
            throw new ValidacaoException("O ID no corpo da requisição não corresponde ao ID da URL.");
        }

        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado para o ID informado.");
        }

        var topico = topicoOptional.get();
        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirTopico(@PathVariable Long id) {
        var topicoOptional = repository.findById(id);
        if (topicoOptional.isEmpty()) {
            throw new ValidacaoException("Tópico não encontrado para o ID informado.");
        }
        var topico = topicoOptional.get();
        topico.excluir(); // ou repository.deleteById(id); para uma exclusão Fisica
        return ResponseEntity.noContent().build();
    }
}