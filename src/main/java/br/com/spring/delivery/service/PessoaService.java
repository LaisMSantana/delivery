package br.com.spring.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.exception.CampoInvalidoException;
import br.com.spring.delivery.exception.EstaSendoUtilizadoException;
import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.dto.PessoaDto;
import br.com.spring.delivery.respository.DeliveryRepository;
import br.com.spring.delivery.respository.PessoaRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	DeliveryRepository deliveryRepository;

	public List<Pessoa> listarTodos() {
		return pessoaRepository.findAll();
	}

	public List<Pessoa> pesquisarPorNome(String nome) {
		return pessoaRepository.findByNome(nome);
	}

	public Optional<Pessoa> pesquisarPorId(Long id) {
		return pessoaRepository.findById(id);
	}

	public Pessoa salvar(Pessoa pessoa) throws CampoInvalidoException {
		return pessoaRepository.save(pessoa);
	}

	public void verificarCampoJaCadastrados(Pessoa pessoa) throws CampoInvalidoException {
		Pessoa mesmoCpf = pessoaRepository.findByCpf(pessoa.getCpf());
		Pessoa mesmoEmail = pessoaRepository.findByEmail(pessoa.getEmail());
		if (mesmoCpf != null && mesmoEmail != null) {
			throw new CampoInvalidoException("Cpf e email já cadastrado.");
		} else if (mesmoCpf != null) {
			throw new CampoInvalidoException("Cpf já cadastrado.");
		} else if (mesmoEmail != null) {
			throw new CampoInvalidoException("Email já cadastrado.");
		}
	}
	
	public void verificarEmailJaCadastrado(PessoaDto pessoa)  throws CampoInvalidoException {
		Pessoa mesmoEmail = pessoaRepository.findByEmail(pessoa.getEmail());
		if (mesmoEmail != null) {
			throw new CampoInvalidoException("Email já cadastrado");
		}
	}

	public void deletar(Long id) throws EstaSendoUtilizadoException {
		if (deliveryRepository.findByPessoaId(id).size() == 0) {
			pessoaRepository.deleteById(id);
		} else {
			throw new EstaSendoUtilizadoException("Não podemos completar a ação, cliente em delivery.");
		}
	}

}
