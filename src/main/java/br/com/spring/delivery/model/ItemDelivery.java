package br.com.spring.delivery.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class ItemDelivery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O produto é obrigatório")
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "produto_id")
	private Produto produto;

	@NotNull(message = "O campo quantidade é obrigatório")
	private Integer quantidade;

	@NotNull(message = "O campo valor total é obrigatório")
	private double valorTotal;

	public ItemDelivery() {
		super();
	}

	public ItemDelivery(Long id, @NotNull(message = "O produto é obrigatório") Produto produto,
			@NotNull(message = "O campo quantidade é obrigatório") Integer quantidade) {
		super();
		this.id = id;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public void calcularValorTotal() {
		this.setValorTotal(this.quantidade * this.produto.getValor());
	}

}
