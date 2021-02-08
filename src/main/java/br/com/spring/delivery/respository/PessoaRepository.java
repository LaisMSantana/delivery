package br.com.spring.delivery.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.spring.delivery.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

	List<Pessoa> findByNome(String nome);

	Pessoa findByCpf(String cpf);

	Pessoa findByEmail(String email);

}
