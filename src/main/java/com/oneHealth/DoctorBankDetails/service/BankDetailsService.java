package com.oneHealth.DoctorBankDetails.service;

import java.util.List;

import com.oneHealth.DoctorBankDetails.entity.BankDetails;
import com.oneHealth.DoctorBankDetails.exception.ProfileNotFoundException;
import com.oneHealth.DoctorBankDetails.exception.RecordNotFoundException;
import com.oneHealth.DoctorBankDetails.exception.DatabaseException;

/**
 * The BankDetailsService interface defines the contract for performing operations related
 * to BankDetails. It declares methods for saving, retrieving, updating, and deleting bank details.
 * @author Anup
 * @version 3.9.10
 */
public interface BankDetailsService 
{
	
	/**
	 * Saves the given BankDetails object to the database.
	 *
	 * @param details The BankDetails object to be saved.
	 * @return The saved BankDetails object.
	 */
	BankDetails saveBankDetails(BankDetails details);
	
	
	
	/**
	 * Retrieves the bank details of a doctor by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor.
	 * @return The BankDetails object corresponding to the given doctor ID.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	BankDetails getBankDetailsByDoctorID(Long doctor_id) throws RecordNotFoundException;
	
	
	
	/**
	 * Retrieves a list of all doctors' bank details.
	 *
	 * @return A list of BankDetails objects representing bank details of all doctors.
	 * @throws DatabaseException If there is an error retrieving data from the database.
	 */
	List<BankDetails> getAllDoctorsBankDetails() throws DatabaseException;
	
	
	
	/**
	 * Updates the bank details of a doctor identified by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor to be updated.
	 * @param details   The updated BankDetails object.
	 * @return The updated BankDetails object.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	BankDetails updatebankDetails(long doctor_id, BankDetails details) throws ProfileNotFoundException;
	
	
	
	/**
	 * Deletes the bank details of a doctor identified by their unique ID.
	 *
	 * @param doctor_id The unique ID of the doctor whose bank details need to be deleted.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor ID.
	 */
	void deleteBankDetails(long doctor_id) throws ProfileNotFoundException;
}
