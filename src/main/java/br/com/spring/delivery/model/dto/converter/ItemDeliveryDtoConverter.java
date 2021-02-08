package br.com.spring.delivery.model.dto.converter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.delivery.model.ItemDelivery;
import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.model.dto.ItemDeliveryDto;
import br.com.spring.delivery.service.ProdutoService;

@Component
public class ItemDeliveryDtoConverter {

	@Autowired
	private ModelMapper modelMapper;

	public static List<ItemDeliveryDto> converterLista(List<ItemDelivery> itens) {
		return itens.stream().map(ItemDeliveryDto::new).collect(Collectors.toList());
	}

	public void converter(ItemDeliveryDto itemDto, ItemDelivery delivery) {
		modelMapper.map(itemDto, delivery);
	}
	
	public static ItemDelivery converterItem(ItemDeliveryDto itemDeliveryDto, ProdutoService produtoService) {
		Optional<Produto> produto = produtoService.pesquisarPorId(itemDeliveryDto.getIdProduto());
		ItemDelivery item = new ItemDelivery();
		if(itemDeliveryDto.getId() != null) {
			item.setId(itemDeliveryDto.getId());
		}
		item.setProduto(produto.get());
		item.setQuantidade(itemDeliveryDto.getQuantidade());
		item.calcularValorTotal();
		return item;
	}

}
