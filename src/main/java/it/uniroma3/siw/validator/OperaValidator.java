package it.uniroma3.siw.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Opera;
import it.uniroma3.siw.service.OperaService;

@Component
public class OperaValidator implements Validator {

	@Autowired
	private OperaService serv;

	@Override
	public void validate(Object o, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "titolo", "required");
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Opera.class.equals(aClass);
	}
}
