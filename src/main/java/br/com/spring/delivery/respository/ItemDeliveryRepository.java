package br.com.spring.delivery.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.ItemDelivery;

public interface ItemDeliveryRepository extends JpaRepository<ItemDelivery, Long>{

}
