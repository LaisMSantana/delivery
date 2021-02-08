package br.com.spring.delivery.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);

	Cliente findByCpf(String cpf);

	Cliente findByEmail(String email);

}
