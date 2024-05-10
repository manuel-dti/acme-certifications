/*
 * RegistrationTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import services.CustomerService;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationTest extends AbstractTest {

	// System under test ------------------------------------------------------

	@Autowired
	private CustomerService	customerService;


	// Tests ------------------------------------------------------------------

	@Test
	public void driver() {
		final Object testingData[][] = {
			{
				"customer1", "announcement6", null
			}, {
				null, "announcement6", IllegalArgumentException.class
			}, {
				"reviewer1", "announcement6", IllegalArgumentException.class
			}, {
				"customer1", "announcement2", IllegalArgumentException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			try {
				super.startTransaction();
				this.template((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);
			} catch (final Throwable oops) {
				throw new RuntimeException(oops);
			} finally {
				super.rollbackTransaction();
			}
	}

	// Ancillary methods ------------------------------------------------------

	protected void template(final String username, final String announcementName, final Class<?> expected) {
		Class<?> caught;
		int announcementId;

		caught = null;
		try {
			super.authenticate(username);
			announcementId = super.getEntityId(announcementName);
			this.customerService.registerPrincipal(announcementId);
			super.unauthenticate();
			super.flushTransaction();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		super.checkExceptions(expected, caught);
	}

}
