package br.com.spring.delivery.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.spring.delivery.model.Delivery;
import br.com.spring.delivery.model.dto.DeliveryDto;
import br.com.spring.delivery.model.dto.converter.DeliveryDtoConverter;
import br.com.spring.delivery.service.DeliveryService;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@Autowired
	DeliveryService deliveryService;	
	@Autowired
	DeliveryDtoConverter deliveryDtoConverter;

	@GetMapping
	public ResponseEntity<List> lista() {
		List<Delivery> deliveries = deliveryService.listarTodos();
		return ResponseEntity.ok(DeliveryDtoConverter.converterLista(deliveries));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DeliveryDto> detalhar(@PathVariable Long id) {
		Optional<Delivery> delivery = deliveryService.pesquisarPorId(id);
		if (delivery.isPresent()) {
			return ResponseEntity.ok(new DeliveryDto(delivery.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DeliveryDto> cadastrar(@RequestBody @Valid DeliveryDto deliveryDto,
			UriComponentsBuilder uriBuilder) {
		Delivery delivery = deliveryService.cadastrar(deliveryDto);

		URI uri = uriBuilder.path("/delivery/{id}").buildAndExpand(delivery.getId()).toUri();
		return ResponseEntity.created(uri).body(new DeliveryDto(delivery));
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<DeliveryDto> atualizar(@PathVariable Long id, @RequestBody @Valid DeliveryDto deliveryDto) {
		Optional<Delivery> optional = deliveryService.pesquisarPorId(id);
		if (optional.isPresent()) {
			Delivery delivery = deliveryService.cadastrar(deliveryDto);
			return ResponseEntity.ok(new DeliveryDto(delivery));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Delivery> optionalPessoa = deliveryService.pesquisarPorId(id);
		if (optionalPessoa.isPresent()) {
			deliveryService.deletar(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
