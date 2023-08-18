package com.oneHealth.DoctorBankDetails.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oneHealth.DoctorBankDetails.entity.BankDetails;
import com.oneHealth.DoctorBankDetails.service.BankDetailsService;

//import jakarta.validation.Valid;

import com.oneHealth.DoctorBankDetails.exception.DatabaseException;
import com.oneHealth.DoctorBankDetails.exception.ProfileNotFoundException;
import com.oneHealth.DoctorBankDetails.exception.RecordNotFoundException;


/**
 * Controller class for handling BankDetails related HTTP requests.
 * @author Anup
 * @version 3.9.10
 */
//@CrossOrigin("*")
@RestController
@RequestMapping("/api/doctors/doctorbankdetails")
public class BankDetailsController 
{
	@Autowired
	private BankDetailsService service;
	
	/**
	 * API endpoint to save bank details for a doctor.
	 *
	 * @param details The BankDetails object containing the bank details to be saved.
	 * @return ResponseEntity<String> A response entity with a success message and HTTP status 201 (CREATED) on successful save.
	 */
	@PostMapping("/savebankdetails")
	public ResponseEntity<String> saveBankDetails(@Validated @RequestBody BankDetails details)
	{
		service.saveBankDetails(details);
		return new ResponseEntity<>("Bank Details Saved Successfully" , HttpStatus.CREATED);
	}
	
	
	
	/**
	 * API endpoint to retrieve bank details of a doctor by their ID.
	 *
	 * @param doctor_id The ID of the doctor for whom to retrieve the bank details.
	 * @return ResponseEntity<BankDetails> A response entity containing the bank details and HTTP status 200 (OK) on success.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor_id.
	 */
	@GetMapping("/getdoctorbankdetails/{id}")
	public ResponseEntity<BankDetails> getBankDetailsByID(@PathVariable(value="id") Long doctor_id) throws RecordNotFoundException
	{
		BankDetails obj = service.getBankDetailsByDoctorID(doctor_id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
	/**
	 * API endpoint to retrieve all doctors' bank details.
	 *
	 * @return ResponseEntity<List<BankDetails>> A response entity containing the list of bank details and HTTP status 200 (OK) on success.
	 * @throws DatabaseException If there is an issue with the database while retrieving the bank details.
	 */
	@GetMapping("/getalldoctorsbankdetails")
	public ResponseEntity<List<BankDetails>> getAllDoctorsBankDetails() throws DatabaseException
	{
		// Retrieve the list of all doctors' bank details from the service layer.
	    List<BankDetails> doctorsBankDetails = service.getAllDoctorsBankDetails();
	    
	    if (doctorsBankDetails.isEmpty()) {
	        // If the list is empty, return a response with a custom message and HTTP status 204 (NO_CONTENT).
	        // The 204 status code indicates that the server successfully processed the request but there is no content to send in the response.
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
	    } else {
	        // If the list is not empty, return the list of doctors' bank details in the response with HTTP status 200 (OK).
	        return new ResponseEntity<>(doctorsBankDetails, HttpStatus.OK);
	    }
	}
	
	
	
	/**
	 * API endpoint to update bank details for a doctor.
	 *
	 * @param doctor_id The ID of the doctor whose bank details need to be updated.
	 * @param details The BankDetails object containing the updated bank details.
	 * @return ResponseEntity<String> A response entity with a success message and HTTP status 200 (OK) on successful update.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor_id.
	 */
	@PutMapping("/updatebankdetails/{id}")
	public ResponseEntity<String> updateDoctorBankDetails(@PathVariable(value="id") Long doctor_id , @RequestBody BankDetails details) throws ProfileNotFoundException
	{
		service.updatebankDetails(doctor_id, details);
		return new ResponseEntity<>("Bank Details Updated Successfully", HttpStatus.OK);
	}
	
	
	
	/**
	 * API endpoint to delete bank details of a doctor by their ID.
	 *
	 * @param doctor_id The ID of the doctor for whom to delete the bank details.
	 * @return ResponseEntity<String> A response entity with a success message and HTTP status 200 (OK) on successful deletion.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor_id.
	 */
	@DeleteMapping("/deletebankdetails/{id}")
	public ResponseEntity<String> deleteBankDetails(@PathVariable(value="id") Long doctor_id) throws ProfileNotFoundException
	{
		service.deleteBankDetails(doctor_id);
		return new ResponseEntity<>("Bank Details Deleted Successfully" , HttpStatus.OK);
	}
}
