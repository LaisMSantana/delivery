package br.com.spring.delivery.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.spring.delivery.model.Endereco;
import br.com.spring.delivery.model.Cliente;
import br.com.spring.delivery.model.TipoDePagamento;

public class ClienteDto {

	private Long id;
	private String nome;
	private String telefone;
	private Endereco endereco;
	private String email;
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;

	public ClienteDto() {
	}

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.telefone = cliente.getTelefone();
		this.endereco = cliente.getEndereco();
		this.email = cliente.getEmail();
		this.tipoDePagamento = cliente.getTipoDePagamento();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoDePagamento getTipoDePagamento() {
		return tipoDePagamento;
	}

	public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
		this.tipoDePagamento = tipoDePagamento;
	}

}
