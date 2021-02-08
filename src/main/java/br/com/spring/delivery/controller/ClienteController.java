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
import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.dto.MensagemDto;
import br.com.spring.delivery.model.dto.ClienteDto;
import br.com.spring.delivery.model.dto.converter.ClienteDtoConverter;
import br.com.spring.delivery.respository.DeliveryRepository;
import br.com.spring.delivery.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	@Autowired
	ClienteDtoConverter clienteDtoConverter;

	@GetMapping
	public ResponseEntity<List> lista(String nome) {
		List<Cliente> clientes;
		if (nome == null) {
			clientes = clienteService.listarTodos();
		} else {
			clientes = clienteService.pesquisarPorNome(nome);
		}
		return ResponseEntity.ok(ClienteDtoConverter.converterLista(clientes));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> detalhar(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.pesquisarPorId(id);
		if (cliente.isPresent()) {
			return ResponseEntity.ok(new ClienteDto(cliente.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder) {
		try {
			clienteService.verificarCampoJaCadastrados(cliente);
			clienteService.salvar(cliente);
		} catch (final CampoInvalidoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
		}

		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDto(cliente));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid ClienteDto clienteDto) {
		Optional<Cliente> optional = clienteService.pesquisarPorId(id);
		if (optional.isPresent()) {
			try {
				Cliente cliente = optional.get();
				if (!cliente.getEmail().equals(clienteDto.getEmail())) {
					clienteService.verificarEmailJaCadastrado(clienteDto);
				}
				clienteDtoConverter.converter(clienteDto, cliente);
				return ResponseEntity.ok(new ClienteDto(clienteService.salvar(cliente)));
			} catch (final CampoInvalidoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
			}
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Cliente> optionalCliente = clienteService.pesquisarPorId(id);
		if (optionalCliente.isPresent()) {
			try {
				clienteService.deletar(id);
				return ResponseEntity.ok().build();
			} catch (EstaSendoUtilizadoException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDto(e.getMessage()));
			}
		}
		return ResponseEntity.notFound().build();
	}

}
