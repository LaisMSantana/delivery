package br.com.spring.delivery.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.model.Delivery;
import br.com.spring.delivery.model.ItemDelivery;
import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.dto.DeliveryDto;
import br.com.spring.delivery.model.dto.ItemDeliveryDto;
import br.com.spring.delivery.model.dto.converter.ItemDeliveryDtoConverter;
import br.com.spring.delivery.respository.DeliveryRepository;

@Service
@Transactional
public class DeliveryService {

	@Autowired
	DeliveryRepository deliveryRepository;

	@Autowired
	ItemDeliveryService itemDeliveryService;
	@Autowired
	ItemDeliveryDtoConverter itemDeliveryDtoConverter;
	@Autowired
	ClienteService clienteService;
	@Autowired
	ProdutoService produtoService;

	public List<Delivery> listarTodos() {
		return deliveryRepository.findAll();
	}

	public Optional<Delivery> pesquisarPorId(Long id) {
		return deliveryRepository.findById(id);
	}

	public Delivery cadastrar(DeliveryDto deliveryDto) {
		List<ItemDelivery> itens = new ArrayList<>();
		cadastrarItens(deliveryDto, itens);

		Optional<Cliente> cliente = clienteService.pesquisarPorId(deliveryDto.getIdCliente());

		Delivery delivery = new Delivery(cliente.get(), itens, cliente.get().getTipoDePagamento());
		delivery.calcularValorTotal();
		if(deliveryDto.getId() != null) {
			delivery.setId(deliveryDto.getId());
		}

		return deliveryRepository.save(delivery);
	}

	private void cadastrarItens(DeliveryDto deliveryDto, List<ItemDelivery> itens) {
		List<ItemDeliveryDto> itemDeliveriesDto = deliveryDto.getItemDeliveriesDto();
		for (int i = 0; i < itemDeliveriesDto.size(); i++) {
			ItemDelivery item = itemDeliveryDtoConverter.converterItem(itemDeliveriesDto.get(i), produtoService);
			itens.add(item);
		}
	}

	public void deletar(Long id) {
		deliveryRepository.deleteById(id);
	}

}
