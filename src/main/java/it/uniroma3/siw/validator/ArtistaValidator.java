package it.uniroma3.siw.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.service.ArtistiService;
import it.uniroma3.siw.model.Artista;

@Component
public class ArtistaValidator implements Validator {

	@Autowired
	private ArtistiService serv;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Artista.class.equals(aClass);
	}
}
