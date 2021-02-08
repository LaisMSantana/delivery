package br.com.spring.delivery.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.Delivery;

public interface DeliveryRepository  extends JpaRepository<Delivery, Long> {

	List<Delivery> findByPessoaId(Long id);
	
	List<Delivery> findByItemDeliveriesProdutoId(Long id);

}
