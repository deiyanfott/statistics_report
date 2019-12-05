package com.coding.exam.hoolah.utils.validator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.utils.validator.CSVTransactionReportValidator;

public class CSVTransactionReportValidatorTest {
	@Test
	public void testIsBlankFilePath() {
		assertEquals(false, CSVTransactionReportValidator.isValidFilePath(null));
	}
	
	@Test
	public void testIsIncorrectFilePath() {
		assertEquals(false, CSVTransactionReportValidator.isValidFilePath("C:/temp/sample.txt"));
	}
	
	@Test
	public void testIsCorrectFilePath() {
		assertEquals(true, CSVTransactionReportValidator.isValidFilePath("C:/temp/sample.csv"));
	}
	
	@Test
	public void testIsValidInputParameters() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertNotNull(params.getStartDate());
		assertNotNull(params.getEndDate());
		assertEquals(true, params.isValidated());
	}
	
	@Test
	public void testIsValidInputParametersNoTime() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018");
		params.setDateTo("20/08/2018");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertNotNull(params.getStartDate());
		assertNotNull(params.getEndDate());
		assertEquals(true, params.isValidated());
	}
	
	@Test
	public void testInvalidInputParameters() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("a");
		params.setDateTo("a");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertNull(params.getStartDate());
		assertNull(params.getEndDate());
		assertEquals(false, params.isValidated());
	}
	
	@Test
	public void testBlankInputParameters() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("");
		params.setDateTo("");
		params.setMerchant("");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertNull(params.getStartDate());
		assertNull(params.getEndDate());
		assertEquals(false, params.isValidated());
	}
	
	@Test
	public void testBlankMerchantParameter() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertNotNull(params.getStartDate());
		assertNotNull(params.getEndDate());
		assertEquals(false, params.isValidated());
	}
}