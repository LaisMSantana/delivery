package br.com.spring.delivery.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.model.ItemDelivery;
import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.model.dto.ItemDeliveryDto;
import br.com.spring.delivery.model.dto.converter.ItemDeliveryDtoConverter;
import br.com.spring.delivery.respository.ItemDeliveryRepository;

@Service
@Transactional
public class ItemDeliveryService {

	@Autowired
	ItemDeliveryRepository itemDeliveryRepository;
	@Autowired
	ProdutoService produtoService;
	@Autowired
	ItemDeliveryDtoConverter itemDeliveryDtoConverter;

	public ItemDelivery getOne(Long id) {
		return itemDeliveryRepository.getOne(id);
	}

	public ItemDelivery cadastrar(ItemDeliveryDto itemDeliveryDto) {
		Optional<Produto> produto = produtoService.pesquisarPorId(itemDeliveryDto.getIdProduto());
		ItemDelivery item = new ItemDelivery();
		item.setProduto(produto.get());
		item.setQuantidade(itemDeliveryDto.getQuantidade());
		item.calcularValorTotal();
		item = itemDeliveryRepository.save(item);
		return item;
	}

	public void deletar(Long id) {
		itemDeliveryRepository.deleteById(id);
	}
}
