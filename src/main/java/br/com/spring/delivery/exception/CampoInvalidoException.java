package br.com.spring.delivery.exception;

public class CampoInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public CampoInvalidoException(final String mensagem) {
		super(mensagem);
	}

	public CampoInvalidoException() {
		super();
	}
}
