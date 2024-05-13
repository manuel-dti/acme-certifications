
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.DemonstrationEntity;
import utilities.AbstractTest;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class DemonstrationServiceTest extends AbstractTest {

	@Autowired
	private DemonstrationService _demonstrationService;


	// Tests -----------------------------------------------------------

	public DemonstrationEntity createDemonstration() {
		DemonstrationEntity d;

		d = this._demonstrationService.create();
		d.setName("test department 1");
		d.setAtribute01("test atribute 01");

		return d;
	}

	public DemonstrationEntity saveDemonstration(final DemonstrationEntity d) {
		DemonstrationEntity saved;
		saved = this._demonstrationService.save(d);

		return saved;
	}

	@Test
	@Transactional
	public void testSaveDemonstration() {
		DemonstrationEntity d, saved;
		Collection<DemonstrationEntity> d_list;

		d = this.createDemonstration();
		saved = this.saveDemonstration(d);

		Assert.isTrue(saved.getId() != 0);
		Assert.isTrue(saved.getName() == d.getName());
		Assert.isTrue(saved.getAtribute01() == d.getAtribute01());

		d_list = this._demonstrationService.findAll();

		Assert.isTrue(d_list.contains(saved));
	}

	@Test
	public void testFindAllDemobstrations() {
		DemonstrationEntity d;
		d = this.createDemonstration();
		d = this.saveDemonstration(d);

		final Collection<DemonstrationEntity> d_list = this._demonstrationService.findAll();

		Assert.isTrue(d_list != null);
		Assert.isTrue(!d_list.isEmpty());
		Assert.isTrue(d_list.contains(d));
	}

	@Test
	@Transactional
	public void testFindOneDemonstration() {
		DemonstrationEntity d, saved, found;
		Collection<DemonstrationEntity> d_list;

		d = this.createDemonstration();
		saved = this.saveDemonstration(d);

		d_list = this._demonstrationService.findAll();
		found = this._demonstrationService.findOne(saved.getId());
		Assert.notNull(found);
		Assert.isTrue(saved.getId() == found.getId());

		Assert.isTrue(d_list.contains(saved));
	}

	@Test
	@Transactional
	public void testDeleteDemonstration() {
		DemonstrationEntity d, saved, found;
		Collection<DemonstrationEntity> d_list;

		d = this.createDemonstration();
		saved = this.saveDemonstration(d);

		d_list = this._demonstrationService.findAll();
		found = this._demonstrationService.findOne(saved.getId());
		Assert.notNull(found);
		Assert.isTrue(saved.getId() == found.getId());

		Assert.isTrue(d_list.contains(saved));

		this._demonstrationService.delete(saved);
		d_list = this._demonstrationService.findAll();

		Assert.isTrue(d_list.contains(saved) == false, "El objeto aún se encuentra");

	}

}
