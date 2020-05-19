package com.example.batchprocessing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor{

	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);
	
	@Override
	public Object process(Object object) throws Exception {
		Person person = (Person)object;
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();
		
		final Person transformedPerson = new Person(firstName, lastName);
		log.info("Converting ("+person+") into ("+transformedPerson+")");
		return transformedPerson;
	}


}
