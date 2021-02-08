package br.com.spring.delivery.model.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.spring.delivery.model.Endereco;
import br.com.spring.delivery.model.Pessoa;
import br.com.spring.delivery.model.TipoDePagamento;

public class PessoaDto {

	private Long id;
	private String nome;
	private String telefone;
	private Endereco endereco;
	private String email;
	@Enumerated(EnumType.STRING)
	private TipoDePagamento tipoDePagamento;

	public PessoaDto() {
	}

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.telefone = pessoa.getTelefone();
		this.endereco = pessoa.getEndereco();
		this.email = pessoa.getEmail();
		this.tipoDePagamento = pessoa.getTipoDePagamento();
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
