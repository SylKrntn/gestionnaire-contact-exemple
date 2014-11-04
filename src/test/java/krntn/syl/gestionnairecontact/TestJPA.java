package krntn.syl.gestionnairecontact;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJPA {

	@Test
	public void dbCreation() {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"application-context.xml"});
		} catch (BeansException e) {
			assertTrue(e.getCause() + ": " + e.getMessage(), false);
		}
		assertTrue(true);
	}

}
