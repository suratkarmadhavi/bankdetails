package com.oneHealth.DoctorBankDetails.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oneHealth.DoctorBankDetails.entity.BankDetails;
import com.oneHealth.DoctorBankDetails.exception.DatabaseException;
import com.oneHealth.DoctorBankDetails.exception.ProfileNotFoundException;
import com.oneHealth.DoctorBankDetails.exception.RecordNotFoundException;
import com.oneHealth.DoctorBankDetails.repository.BankDetailsRepository;
import com.oneHealth.DoctorBankDetails.service.BankDetailsService;

/**
 * The BankDetailsServiceImplementation class is responsible for implementing the business logic
 * for managing bank details of doctors. It interacts with the BankDetailsRepository to perform CRUD operations.
 * @author Anup
 * @version 3.9.10
 */
@Service
public class BankDetailsServiceImplementation implements BankDetailsService {

	@Autowired
	private BankDetailsRepository repo;
	
	/**
	 * Saves the bank details of a doctor to the database.
	 *
	 * @param details The BankDetails object containing the bank details to be saved.
	 * @return The saved BankDetails object.
	 */
	@Override
	public BankDetails saveBankDetails(BankDetails details) {
		return repo.save(details);
	}

	/**
	 * Retrieves the bank details of a doctor by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor.
	 * @return The BankDetails object corresponding to the given doctor ID.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	@Override
	public BankDetails getBankDetailsByDoctorID(Long doctor_id) throws RecordNotFoundException {
		return repo.findById(doctor_id).orElseThrow(
                () -> new RecordNotFoundException("No Records Found With This Doctor: " + doctor_id)
        );
	}

	/**
	 * Retrieves a list of all doctors' bank details.
	 *
	 * @return A list of BankDetails objects representing bank details of all doctors.
	 * @throws DatabaseException If there is an error retrieving data from the database.
	 */
	@Override
	public List<BankDetails> getAllDoctorsBankDetails() throws DatabaseException {
		return repo.findAll();
	}

	/**
	 * Updates the bank details of a doctor identified by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor to be updated.
	 * @param details   The updated BankDetails object.
	 * @return The updated BankDetails object.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	@Override
	public BankDetails updatebankDetails(long doctor_id, BankDetails details) throws ProfileNotFoundException {
		BankDetails obj =  repo.findById(doctor_id).orElseThrow(
                () -> new ProfileNotFoundException("No Profile Found With This ID: " + doctor_id)
        );
		
		obj.setBank_name(details.getBank_name());
		obj.setAcc_number(details.getAcc_number());
		obj.setIfsc(details.getIfsc());
		obj.setUpi_id(details.getUpi_id());
		obj.setAddress(details.getAddress());
		obj.setGst_number(details.getGst_number());
		obj.setPan_number(details.getPan_number());
		obj.setAadhar_number(details.getAadhar_number());
		
		return repo.save(obj);
	}

	/**
	 * Deletes the bank details of a doctor identified by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor whose bank details need to be deleted.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	@Override
	public void deleteBankDetails(long doctor_id) throws ProfileNotFoundException {
		BankDetails obj =  repo.findById(doctor_id).orElseThrow(
                () -> new ProfileNotFoundException("No Profile Found With This ID: " + doctor_id)
        );
		
	   repo.delete(obj);
	}

}
