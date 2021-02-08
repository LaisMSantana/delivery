package br.com.spring.delivery.exception;

public class EstaSendoUtilizadoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EstaSendoUtilizadoException(final String mensagem) {
		super(mensagem);
	}

	public EstaSendoUtilizadoException() {
		super();
	}
}
