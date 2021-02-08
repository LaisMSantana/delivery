package br.com.spring.delivery.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MensagemDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date timestamp;

	private Integer status;

	private String erro;

	private String mensagem;

	private MensagemDto(final Builder builder) {
		timestamp = builder.timestamp;
		status = builder.status;
		erro = builder.erro;
		mensagem = builder.mensagem;
	}

	public MensagemDto(final String mensagem) {
		this.mensagem = mensagem;
	}

	public MensagemDto() {
	}

	public static long getSerialversionUid() {
		return serialVersionUID;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public String getErro() {
		return erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Date timestamp;

		private Integer status;

		private String erro;

		private String mensagem;

		private Builder() {
		}

		public Builder withTimestamp(final Date timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder withStatus(final Integer status) {
			this.status = status;
			return this;
		}

		public Builder withErro(final String erro) {
			this.erro = erro;
			return this;
		}

		public Builder withMensagem(final String mensagem) {
			this.mensagem = mensagem;
			return this;
		}

		public MensagemDto build() {
			return new MensagemDto(this);
		}
	}
}
