package br.com.spring.delivery.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.delivery.model.Delivery;
import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.dto.DeliveryDto;
import br.com.spring.delivery.model.dto.PessoaDto;

@Component
public class DeliveryDtoConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public static List<DeliveryDto> converterLista(List<Delivery> deliveries) {
		return deliveries.stream().map(DeliveryDto::new).collect(Collectors.toList());
	}
	
	public void converter(DeliveryDto deliveryDto, Delivery delivery) {
		modelMapper.map(deliveryDto, delivery);
	}

}
