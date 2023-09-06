package com.jpa.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.oneHealth.DoctorBankDetails.OneHealthDoctorBankDetailsServiceApplication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(classes = OneHealthDoctorBankDetailsServiceApplication.class)
public class BankControllerTest {


    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "http://localhost"; // Set the base URI to your application's base URL
        RestAssured.port = 8080; // Set the port number to your application's port
    }

    @Test
    public void testSaveBankDetails() {
        // Create a JSON representation of your BankDetails object
        String requestBody = "{\"bank_name\":\"Your Bank Name\",\"acc_number\":123456,\"ifsc\":\"IFSC123\",\"upi_id\":\"UPI123\",\"address\":\"Address\",\"pan_number\":\"PAN123\",\"aadhar_number\":\"Aadhar123\",\"gst_number\":\"GST123\"}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/doctors/doctorbankdetails/savebankdetails")
                .then()
                .statusCode(201) // Expecting HTTP status code 201 (CREATED)
                .body(equalTo("Bank Details Saved Successfully"))
                .extract()
                .response();
        
        System.out.println("Status Code = "+response.getStatusCode());

        // You can add additional assertions to validate the response further if needed
    }
    
    
    @Test
    public void testGetAllDoctorsBankDetails() {
        // Send a GET request to the endpoint
        Response response = given()
            .contentType(ContentType.JSON)
            .when()
            .get("/api/doctors/doctorbankdetails/getalldoctorsbankdetails")
            .then()
            .statusCode(200) // Expecting HTTP status code 200 (OK)
            .contentType(ContentType.JSON)
            .extract()
            .response();
        
    }
}
