package br.com.spring.delivery.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.dto.ClienteDto;

@Component
public class ClienteDtoConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public static List<ClienteDto> converterLista(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
	}
	
	public void converter(ClienteDto clienteDto, Cliente cliente) {
		modelMapper.map(clienteDto, cliente);
	}

}
