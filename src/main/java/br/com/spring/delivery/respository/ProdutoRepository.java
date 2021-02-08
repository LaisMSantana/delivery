package br.com.spring.delivery.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.Produto;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

	List<Produto> findByNome(String produto);

	

}
