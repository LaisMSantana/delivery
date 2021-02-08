package br.com.spring.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.spring.delivery.exception.CampoInvalidoException;
import br.com.spring.delivery.exception.EstaSendoUtilizadoException;
import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.dto.ClienteDto;
import br.com.spring.delivery.respository.DeliveryRepository;
import br.com.spring.delivery.respository.ClienteRepository;

@Service
@Transactional
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DeliveryRepository deliveryRepository;

	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	public List<Cliente> pesquisarPorNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public Optional<Cliente> pesquisarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	public Cliente salvar(Cliente cliente) throws CampoInvalidoException {
		return clienteRepository.save(cliente);
	}

	public void verificarCampoJaCadastrados(Cliente cliente) throws CampoInvalidoException {
		Cliente mesmoCpf = clienteRepository.findByCpf(cliente.getCpf());
		Cliente mesmoEmail = clienteRepository.findByEmail(cliente.getEmail());
		if (mesmoCpf != null && mesmoEmail != null) {
			throw new CampoInvalidoException("Cpf e email já cadastrado.");
		} else if (mesmoCpf != null) {
			throw new CampoInvalidoException("Cpf já cadastrado.");
		} else if (mesmoEmail != null) {
			throw new CampoInvalidoException("Email já cadastrado.");
		}
	}
	
	public void verificarEmailJaCadastrado(ClienteDto cliente)  throws CampoInvalidoException {
		Cliente mesmoEmail = clienteRepository.findByEmail(cliente.getEmail());
		if (mesmoEmail != null) {
			throw new CampoInvalidoException("Email já cadastrado");
		}
	}

	public void deletar(Long id) throws EstaSendoUtilizadoException {
		if (deliveryRepository.findByClienteId(id).size() == 0) {
			clienteRepository.deleteById(id);
		} else {
			throw new EstaSendoUtilizadoException("Não podemos completar a ação, cliente em delivery.");
		}
	}

}
