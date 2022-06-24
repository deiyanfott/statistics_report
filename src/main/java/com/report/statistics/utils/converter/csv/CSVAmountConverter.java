package com.report.statistics.utils.converter.csv;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

import java.math.BigDecimal;

public class CSVAmountConverter extends AbstractBeanField<BigDecimal, String> {
	@Override
	protected Object convert(String amountString) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		if (StringUtils.isNotBlank(amountString)) {
			return new BigDecimal(amountString);
		} else {
			System.err.println("Amount input is missing.");
		}
		
		return null;
	}
}
