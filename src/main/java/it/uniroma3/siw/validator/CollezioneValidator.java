package it.uniroma3.siw.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Collezione;
import it.uniroma3.siw.service.CollezioneService;

@Component
public class CollezioneValidator implements Validator {

	@Autowired
	private CollezioneService serv;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Collezione.class.equals(aClass);
	}
}
