package com.coding.exam.hoolah.txn.report.service.impl.csv;

import java.util.Scanner;

import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.txn.report.service.TransactionReportScannerService;

public class CSVTransactionReportScannerServiceImpl implements TransactionReportScannerService {
	@Override
	public CSVInputParameters scanInputParameters() {
		String dateFrom = null;
		String dateTo = null;
		String merchant = null;
				
		try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Please enter starting date and time (dd/MM/yyyy HH:hh:ss): ");
			dateFrom = scanner.nextLine();
			
			System.out.println("Please enter ending date and time range (dd/MM/yyyy HH:hh:ss): ");
			dateTo = scanner.nextLine();
			
			System.out.println("Please enter merchant name: ");
			merchant = scanner.nextLine();
		}
		
		return new CSVInputParameters(dateFrom, dateTo, merchant.trim(), false, null, null);
	}

}
