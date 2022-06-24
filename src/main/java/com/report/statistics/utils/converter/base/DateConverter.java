package com.report.statistics.utils.converter.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.report.statistics.constants.CSVTransactionReportConstants;
import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public abstract class DateConverter extends AbstractBeanField<Object, String> {
	protected abstract Object convert(String dateString) throws CsvDataTypeMismatchException, CsvConstraintViolationException;
	
	public static Date parseDateString(String input, boolean isStart) {
		if (StringUtils.isNotBlank(input)) {
			StringBuilder dateString = new StringBuilder(input.trim());
			SimpleDateFormat sdf = new SimpleDateFormat(CSVTransactionReportConstants.CSV_DATE_TIME_FORMAT);
			
			try {
				return sdf.parse(dateString.toString());
			} catch (ParseException ex) {
				if (isStart) {
					System.out.println(String.format("Setting %s as default start time.",
							CSVTransactionReportConstants.DEFAULT_START_TIME));
					dateString.append(CSVTransactionReportConstants.DEFAULT_START_TIME);
				} else {
					System.out.println(String.format("Setting %s as default end time.",
							CSVTransactionReportConstants.DEFAULT_END_TIME));
					dateString.append(CSVTransactionReportConstants.DEFAULT_END_TIME);
				}
				
				try {
					return sdf.parse(dateString.toString());
				} catch (ParseException e) {
					System.err.println("Invalid date format.");
				}
			}
		} else {
			System.err.println(String.format("%s date input is missing.", isStart ? "Start" : "End"));
		}
		
		return null;
	}
}
