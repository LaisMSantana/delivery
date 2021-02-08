package br.com.spring.delivery.model.dto;

import br.com.spring.delivery.model.ItemDelivery;

public class ItemDeliveryDto {

	private Long id;
	private Long idProduto;
	private int quantidade;
	private double valorTotal;

	public ItemDeliveryDto() {
	}

	public ItemDeliveryDto(ItemDelivery itemDelivery) {
		this.id = itemDelivery.getId();
		this.idProduto = itemDelivery.getProduto().getId();
		this.quantidade = itemDelivery.getQuantidade();
		this.valorTotal = itemDelivery.getValorTotal();
	}

	public ItemDeliveryDto(Long idProduto, int quantidade) {
		super();
		this.idProduto = idProduto;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}


}
