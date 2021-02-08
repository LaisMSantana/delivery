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

import br.com.spring.delivery.exception.CampoInvalidoException;
import br.com.spring.delivery.exception.EstaSendoUtilizadoException;
import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.dto.MensagemDto;
import br.com.spring.delivery.model.dto.PessoaDto;
import br.com.spring.delivery.model.dto.converter.PessoaDtoConverter;
import br.com.spring.delivery.respository.DeliveryRepository;
import br.com.spring.delivery.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	@Autowired
	PessoaDtoConverter pessoaDtoConverter;

	@GetMapping
	public ResponseEntity<List> lista(String nome) {
		List<Pessoa> pessoas;
		if (nome == null) {
			pessoas = pessoaService.listarTodos();
		} else {
			pessoas = pessoaService.pesquisarPorNome(nome);
		}
		return ResponseEntity.ok(PessoaDtoConverter.converterLista(pessoas));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDto> detalhar(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaService.pesquisarPorId(id);
		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new PessoaDto(pessoa.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid Pessoa pessoa, UriComponentsBuilder uriBuilder) {
		try {
			pessoaService.verificarCampoJaCadastrados(pessoa);
			pessoaService.salvar(pessoa);
		} catch (final CampoInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
		}

		URI uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaDto pessoaDto) {
		Optional<Pessoa> optional = pessoaService.pesquisarPorId(id);
		if (optional.isPresent()) {
			try {
				Pessoa pessoa = optional.get();
				if (!pessoa.getEmail().equals(pessoaDto.getEmail())) {
					pessoaService.verificarEmailJaCadastrado(pessoaDto);
				}
				pessoaDtoConverter.converter(pessoaDto, pessoa);
				return ResponseEntity.ok(new PessoaDto(pessoaService.salvar(pessoa)));
			} catch (final CampoInvalidoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Pessoa> optionalPessoa = pessoaService.pesquisarPorId(id);
		if (optionalPessoa.isPresent()) {
			try {
				pessoaService.deletar(id);
				return ResponseEntity.ok().build();
			} catch (EstaSendoUtilizadoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
			}
		}
		return ResponseEntity.notFound().build();
	}

}
