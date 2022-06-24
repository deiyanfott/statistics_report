package com.report.statistics.txn.report.service.impl.csv;

import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.model.csv.CSVInputParameters;
import com.report.statistics.txn.report.service.TransactionReportFilterService;
import com.report.statistics.txn.report.service.TransactionReportScannerService;
import com.report.statistics.txn.report.service.TransactionReportStatisticsService;
import com.report.statistics.utils.validator.CSVTransactionReportValidator;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

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
				System.out.println(String.format("Average Transaction Value = %.2f",
						statistics.getAverage().setScale(2, RoundingMode.DOWN)));
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