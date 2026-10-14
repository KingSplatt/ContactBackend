package com.example.contatcs_project;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.contatcs_project.dto.ContactDTO;

@SpringBootTest
class ContatcsProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void contactDtoStoresName() {
		ContactDTO contact = new ContactDTO();

		contact.setNombre("Maria");

		assertEquals("Maria", contact.getNombre());
	}

}
