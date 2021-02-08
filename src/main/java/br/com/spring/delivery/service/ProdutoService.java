package br.com.spring.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.exception.EstaSendoUtilizadoException;
import br.com.spring.delivery.model.Produto;
import br.com.spring.delivery.respository.DeliveryRepository;
import br.com.spring.delivery.respository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	@Autowired
	DeliveryRepository deliveryRepository;

	public List<Produto> listarTodos() {
		return produtoRepository.findAll();
	}

	public List<Produto> pesquisarPorNome(String nome) {
		return produtoRepository.findByNome(nome);
	}

	public Optional<Produto> pesquisarPorId(Long id) {
		return produtoRepository.findById(id);
	}

	public Produto getOne(Long id) {
		return produtoRepository.getOne(id);
	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}

	public void deletar(Long id) throws EstaSendoUtilizadoException {
		if (deliveryRepository.findByItemDeliveriesProdutoId(id).size() == 0) {
			produtoRepository.deleteById(id);
		} else {
			throw new EstaSendoUtilizadoException("Não podemos completar a ação, produto em delivery.");
		}
	}
}
