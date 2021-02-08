package br.com.spring.delivery.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.spring.delivery.exception.EstaSendoUtilizadoException;
import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.model.dto.MensagemDto;
import br.com.spring.delivery.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List> lista(String produto) {
		List<Produto> produtos;
		if (produto == null) {
			produtos = produtoService.listarTodos();
		} else {
			produtos = produtoService.pesquisarPorNome(produto);
		}
		return ResponseEntity.ok(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> detalhar(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.pesquisarPorId(id);
		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Produto> cadastrar(@RequestBody @Valid Produto produto, UriComponentsBuilder uriBuilder) {
		produtoService.salvar(produto);

		URI uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(produto);
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produto) {
		Optional<Produto> optional = produtoService.pesquisarPorId(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(produtoService.salvar(produto));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Produto> optional = produtoService.pesquisarPorId(id);
		if (optional.isPresent()) {
			try {
				produtoService.deletar(id);
				return ResponseEntity.ok().build();
			} catch (EstaSendoUtilizadoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
			}
		}
		return ResponseEntity.notFound().build();
	}
}
