package br.com.spring.delivery.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.Endereco;

public interface EnderecoRepository  extends JpaRepository<Endereco, Long> {

}
