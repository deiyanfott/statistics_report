package com.coding.exam.hoolah.txn.report.service.impl.csv;

import java.util.List;
import java.util.Scanner;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.txn.report.service.TransactionReportFilterService;
import com.coding.exam.hoolah.txn.report.service.TransactionReportScannerService;
import com.coding.exam.hoolah.txn.report.service.TransactionReportStatisticsService;
import com.coding.exam.hoolah.utils.validator.CSVTransactionReportValidator;

public class CSVTransactionReportStatisticsServiceImpl implements TransactionReportStatisticsService {
	@Override
	public void showStatisticalData(List<TransactionReport> csvReportList) {
		try (Scanner scanner = new Scanner(System.in)) {
			CSVInputParameters csvInputParameters = scanAndValidateInputParameters();
			
			if (csvInputParameters.isValidated()) {
				TransactionReportFilterService filterSvc = new CSVTransactionReportFilterServiceImpl();
				BigDecimalSummaryStatistics statistics = filterSvc
						.filterStatisticalData(csvReportList, csvInputParameters);
				
				System.out.println(String.format("Number of transactions = %d", statistics.getCount()));
				System.out.println(String.format("Average Transaction Value = %.2f", statistics.getAverage()));
			} else {
				System.err.println("Invalid start/end date format or merchant is empty. Please try again.");
			}
		}
	}
	
	private CSVInputParameters scanAndValidateInputParameters() {
		TransactionReportScannerService scannerSvc = new CSVTransactionReportScannerServiceImpl();
		CSVInputParameters csvInputParameters = scannerSvc.scanInputParameters();
		CSVTransactionReportValidator.validateAndSetInputParameters(csvInputParameters);
		return csvInputParameters;
	}
}