package com.oneHealth.DoctorBankDetails.controller;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

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
	private static final Logger logger = Logger.getLogger(BankDetailsController.class.getName());
	@Autowired
	private BankDetailsService service;
	
	/**
	 * API endpoint to save bank details for a doctor.
	 *
	 * @param details The BankDetails object containing the bank details to be saved.
	 * @return ResponseEntity<String> A response entity with a success message and HTTP status 201 (CREATED) on successful save.
	 */
//
	
	@PostMapping("/savebankdetails")
    public ResponseEntity<String> saveBankDetails(@Validated @RequestBody BankDetails details) {
        try {
            service.saveBankDetails(details);
            return new ResponseEntity<>("Bank Details Saved Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception using the logger
        	logger.info("An error occurred while saving bank details");
            return new ResponseEntity<>("An error occurred while saving bank details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	/**
	 * API endpoint to retrieve bank details of a doctor by their ID.
	 *
	 * @param doctor_id The ID of the doctor for whom to retrieve the bank details.
	 * @return ResponseEntity<BankDetails> A response entity containing the bank details and HTTP status 200 (OK) on success.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor_id.
	 */
	 @GetMapping("/getdoctorbankdetails/{id}")
	    public ResponseEntity<BankDetails> getBankDetailsByID(@PathVariable(value="id") Long doctor_id) {
	        try {
	            BankDetails obj = service.getBankDetailsByDoctorID(doctor_id);
	            return ResponseEntity.ok().body(obj);
	        } catch (RecordNotFoundException e) {
	            // Log the RecordNotFoundException
	            logger.info("Record not found for doctor_id: {}");
	            // Handle the exception or return a custom response
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        } catch (Exception e) {
	            // Log other exceptions
	            logger.info("An error occurred while retrieving bank details for doctor_id: {}");
	            // Handle other exceptions or return a custom response
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
	
	/**
	 * API endpoint to retrieve all doctors' bank details.
	 *
	 * @return ResponseEntity<List<BankDetails>> A response entity containing the list of bank details and HTTP status 200 (OK) on success.
	 * @throws DatabaseException If there is an issue with the database while retrieving the bank details.
	 */
	 @GetMapping("/getalldoctorsbankdetails")
	    public ResponseEntity<List<BankDetails>> getAllDoctorsBankDetails() {
	        try {
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
	        } catch (DatabaseException e) {
	            // Log the DatabaseException
	            logger.info("An error occurred while retrieving doctors' bank details");
	            // Handle the DatabaseException or return a custom response
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
	        } catch (Exception e) {
	            // Log other exceptions
	            logger.info("An error occurred while processing the request");
	            // Handle other exceptions or return a custom response
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
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
	    public ResponseEntity<String> updateDoctorBankDetails(@PathVariable(value = "id") Long doctor_id, @RequestBody BankDetails details) {
	        try {
	            service.updatebankDetails(doctor_id, details);
	            return new ResponseEntity<>("Bank Details Updated Successfully", HttpStatus.OK);
	        } catch (ProfileNotFoundException e) {
	            // Log the ProfileNotFoundException
	            logger.info("Profile not found for doctor_id: {}");
	            // Handle the ProfileNotFoundException or return a custom response
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found for doctor_id: " + doctor_id);
	        } catch (Exception e) {
	            // Log other exceptions
	            logger.info("An error occurred while updating bank details for doctor_id: {}");
	            // Handle other exceptions or return a custom response
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating bank details");
	        }
	    }
	
	
	
	/**
	 * API endpoint to delete bank details of a doctor by their ID.
	 *
	 * @param doctor_id The ID of the doctor for whom to delete the bank details.
	 * @return ResponseEntity<String> A response entity with a success message and HTTP status 200 (OK) on successful deletion.
	 * @throws ProfileNotFoundException If no bank details are found for the given doctor_id.
	 */
	 @DeleteMapping("/deletebankdetails/{id}")
	    public ResponseEntity<String> deleteBankDetails(@PathVariable(value = "id") Long doctor_id) {
	        try {
	            service.deleteBankDetails(doctor_id);
	            return new ResponseEntity<>("Bank Details Deleted Successfully", HttpStatus.OK);
	        } catch (ProfileNotFoundException e) {
	            // Log the ProfileNotFoundException
	            logger.info("Profile not found for doctor_id: {}");
	            // Handle the ProfileNotFoundException or return a custom response
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found for doctor_id: " + doctor_id);
	        } catch (Exception e) {
	            // Log other exceptions
	            logger.info("An error occurred while deleting bank details for doctor_id: {}");
	            // Handle other exceptions or return a custom response
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting bank details");
	        }
	    }
}
