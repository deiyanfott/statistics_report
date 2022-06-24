package com.report.statistics.utils.converter.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

import com.report.statistics.constants.CSVTransactionReportConstants;
import com.report.statistics.utils.converter.base.DateConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CSVDateConverter extends DateConverter {
	@Override
	protected Object convert(String dateString) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		if (StringUtils.isNotBlank(dateString)) {
			SimpleDateFormat sdf = new SimpleDateFormat(CSVTransactionReportConstants.CSV_DATE_TIME_FORMAT);
			
			try {
				return sdf.parse(dateString);
			} catch (ParseException e) {
				System.err.println("Invalid date format.");
			}
		} else {
			System.err.println("Date input is missing.");
		}
		
		return null;
	}
}