/*
 * StringToRegistrationConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.RegistrationRepository;
import domain.Registration;

@Component
@Transactional
public class StringToRegistrationConverter implements Converter<String, Registration> {

	@Autowired
	RegistrationRepository	registrationRepository;


	@Override
	public Registration convert(final String text) {
		Registration result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = this.registrationRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
