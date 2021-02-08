package br.com.spring.delivery.model.dto.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.dto.PessoaDto;

@Component
public class PessoaDtoConverter {
	
	@Autowired
	private ModelMapper modelMapper;

	public static List<PessoaDto> converterLista(List<Pessoa> pessoas) {
		return pessoas.stream().map(PessoaDto::new).collect(Collectors.toList());
	}
	
	public void converter(PessoaDto pessoaDto, Pessoa pessoa) {
		modelMapper.map(pessoaDto, pessoa);
	}

}
