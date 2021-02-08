package br.com.spring.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.model.Endereco;
import br.com.spring.delivery.respository.EnderecoRepository;

@Service
@Transactional
public class EnderecoService {

	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco cadastrar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public Endereco getOne(Long id) {
		return enderecoRepository.getOne(id);
	}
	
	public void deletar(Long id) {
		enderecoRepository.deleteById(id);
	}
}
