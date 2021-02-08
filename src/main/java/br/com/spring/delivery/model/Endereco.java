package br.com.spring.delivery.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Endereco {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O campo logradouro é obrigatório")
	private String logradouro;

	@NotBlank(message = "O campo cidade é obrigatório")
	private String cidade;

	@NotBlank(message = "O campo estado é obrigatório")
	private String estado;

	@NotBlank(message = "O campo cep é obrigatório")
	@Size(min = 8)
	private String cep;

	@NotBlank(message = "O campo número é obrigatório")
	private String numero;

	private String complemento;

	public Endereco() {
	}

	public Endereco(Long id, @NotBlank(message = "O campo logradouro é obrigatório") String logradouro,
			@NotBlank(message = "O campo cidade é obrigatório") String cidade,
			@NotBlank(message = "O campo estado é obrigatório") String estado,
			@NotBlank(message = "O campo cep é obrigatório") @Size(min = 8) String cep,
			@NotBlank(message = "O campo número é obrigatório") String numero, String complemento) {
		super();
		this.id = id;
		this.logradouro = logradouro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
