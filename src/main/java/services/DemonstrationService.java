
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.DemonstrationEntity;
import repositories.DemonstrationRepository;

@Service
@Transactional
public class DemonstrationService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private DemonstrationRepository _demonstrationRepository;


	// Constructors -----------------------------------------------------------

	public DemonstrationService() {
		super();
	}

	// CRUD ---------------------

	public DemonstrationEntity create() {
		DemonstrationEntity result;

		result = new DemonstrationEntity();

		return result;
	}

	public Collection<DemonstrationEntity> findAll() {
		Collection<DemonstrationEntity> result;

		result = this._demonstrationRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public DemonstrationEntity findOne(final int id) {
		DemonstrationEntity result;

		result = this._demonstrationRepository.findOne(id);
		Assert.notNull(result);

		return result;
	}

	public DemonstrationEntity save(final DemonstrationEntity d) {
		Assert.notNull(d);

		DemonstrationEntity result;
		result = this._demonstrationRepository.save(d);

		Assert.notNull(result);
		return result;
	}

	public void delete(final DemonstrationEntity d) {
		Assert.notNull(d);
		Assert.isTrue(d.getId() != 0);
		this._demonstrationRepository.delete(d);
	}

}
