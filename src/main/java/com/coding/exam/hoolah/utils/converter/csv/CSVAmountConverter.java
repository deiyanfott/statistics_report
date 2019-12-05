package com.coding.exam.hoolah.utils.converter.csv;

import org.apache.commons.lang3.StringUtils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class CSVAmountConverter extends AbstractBeanField<Double, String> {
	@Override
	protected Object convert(String amountString) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
		if (StringUtils.isNotBlank(amountString)) {
			return Double.parseDouble(amountString);
		} else {
			System.err.println("Amount input is missing.");
		}
		
		return null;
	}
}
