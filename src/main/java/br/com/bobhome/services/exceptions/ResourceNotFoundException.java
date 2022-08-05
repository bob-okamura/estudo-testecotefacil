package br.com.bobhome.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Long id) {
		super("Entity " + id + " not found.");
	}

}
