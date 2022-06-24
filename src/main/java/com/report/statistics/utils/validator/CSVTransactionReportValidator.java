package com.report.statistics.utils.validator;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import com.report.statistics.constants.CSVTransactionReportConstants;
import com.report.statistics.constants.ErrorConstants;
import com.report.statistics.model.csv.CSVInputParameters;
import com.report.statistics.utils.converter.base.DateConverter;

public class CSVTransactionReportValidator {
	private CSVTransactionReportValidator() {
		
	}
	
	public static boolean isValidFilePath(String filePath) {
		if (StringUtils.isBlank(filePath)) {
			System.err.println(ErrorConstants.NO_FILE_PATH_SPECIFIED);
			return false;
		} else if (!filePath.endsWith(CSVTransactionReportConstants.FILE_EXTENSION)) {
			System.err.println(ErrorConstants.INVALID_FILE_EXTENSION);
			return false;
		} else {
			return true;
		}
	}
	
	public static void validateAndSetInputParameters(CSVInputParameters csvInputParameters) {
		Date startDate = DateConverter.parseDateString(csvInputParameters.getDateFrom(), true);
		Date endDate = DateConverter.parseDateString(csvInputParameters.getDateTo(), false);
		
		csvInputParameters.setStartDate(startDate);
		csvInputParameters.setEndDate(endDate);
		csvInputParameters.setValidated(Objects.nonNull(startDate) && Objects.nonNull(endDate)
				&& StringUtils.isNotBlank(csvInputParameters.getMerchant())); 
	}
}