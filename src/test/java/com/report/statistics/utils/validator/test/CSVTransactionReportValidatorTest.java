package com.report.statistics.utils.validator.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.report.statistics.model.csv.CSVInputParameters;
import com.report.statistics.utils.validator.CSVTransactionReportValidator;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CSVTransactionReportValidatorTest {
	@Test
	public void testIsBlankFilePath() {
		Assert.assertEquals(false, CSVTransactionReportValidator.isValidFilePath(null));
	}
	
	@Test
	public void testIsIncorrectFilePath() {
		assertEquals(false, CSVTransactionReportValidator.isValidFilePath("src/test/resources/TestFile.txt"));
	}
	
	@Test
	public void testIsCorrectFilePath() {
		assertEquals(true, CSVTransactionReportValidator.isValidFilePath("src/test/resources/TestFile.csv"));
	}
	
	@Test
	public void testIsValidInputParameters() throws ParseException {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("Kwik-E-Mart");

		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		assertEquals(formatter.parse("20/08/2018 12:00:00"), params.getStartDate());
		assertEquals(formatter.parse("20/08/2018 13:00:00"), params.getEndDate());
		assertEquals(true, params.isValidated());
	}
	
	@Test
	public void testIsValidInputParametersNoTime() throws ParseException {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018");
		params.setDateTo("20/08/2018");
		params.setMerchant("Kwik-E-Mart");

		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		assertEquals(formatter.parse("20/08/2018 00:00:00"), params.getStartDate());
		assertEquals(formatter.parse("20/08/2018 23:59:59"), params.getEndDate());
		assertEquals(true, params.isValidated());
	}
	
	@Test
	public void testInvalidInputParameters() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("a");
		params.setDateTo("a");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertEquals(false, params.isValidated());
	}
	
	@Test
	public void testBlankInputParameters() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("");
		params.setDateTo("");
		params.setMerchant("");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		
		assertEquals(false, params.isValidated());
	}
	
	@Test
	public void testBlankMerchantParameter() throws ParseException {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("");

		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		assertEquals(formatter.parse("20/08/2018 12:00:00"), params.getStartDate());
		assertEquals(formatter.parse("20/08/2018 13:00:00"), params.getEndDate());
		assertEquals(false, params.isValidated());
	}
}