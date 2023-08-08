package com.oneHealth.DoctorBankDetails.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



/**
 * Entity class representing the BankDetails information of a doctor.
 * This class is mapped to a database table to store the bank details of doctors.
 * @author Anup
 * @version 3.9.10
 */
@Entity
public class BankDetails 
{
	@Id
	private long doctor_id;
	@NotBlank(message="Bank Name is Required")
	private String bank_name;
	@NotNull
	private long acc_number;
	@NotBlank
	private String ifsc;
	@NotBlank
	private String upi_id;
	@NotBlank
	private String address;
	@NotBlank
	private String pan_number;
	@NotBlank
	private String aadhar_number;
	@NotBlank
	private String gst_number;
	
	
	// Constructors, getters, and setters are provided below...
	
	public BankDetails() {
		
	}

	public BankDetails(long doctor_id, String bank_name, long acc_number, String ifsc, String upi_id,
			String address, String pan_number, String aadhar_number, String gst_number) {
		super();
		this.doctor_id = doctor_id;
		this.bank_name = bank_name;
		this.acc_number = acc_number;
		this.ifsc = ifsc;
		this.upi_id = upi_id;
		this.address = address;
		this.pan_number = pan_number;
		this.aadhar_number = aadhar_number;
		this.gst_number = gst_number;
	}

	
	public long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public long getAcc_number() {
		return acc_number;
	}

	public void setAcc_number(long acc_number) {
		this.acc_number = acc_number;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getUpi_id() {
		return upi_id;
	}

	public void setUpi_id(String upi_id) {
		this.upi_id = upi_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPan_number() {
		return pan_number;
	}

	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}

	public String getAadhar_number() {
		return aadhar_number;
	}

	public void setAadhar_number(String aadhar_number) {
		this.aadhar_number = aadhar_number;
	}

	public String getGst_number() {
		return gst_number;
	}

	public void setGst_number(String gst_number) {
		this.gst_number = gst_number;
	}
	
	
	
	
	
}
