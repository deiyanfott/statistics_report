package com.report.statistics;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.txn.report.service.TransactionReportInitializerService;
import com.report.statistics.txn.report.service.TransactionReportStatisticsService;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportStatisticsServiceImpl;

public class ReportGenerator {
    public static void main(String[] args) {
    	try {
    		TransactionReportInitializerService txnReportSvc = new CSVTransactionReportInitializerServiceImpl();
    		List<TransactionReport> csvReportList = txnReportSvc.initializeData(args[0]);
    		TransactionReportStatisticsService txnStatSvc = new CSVTransactionReportStatisticsServiceImpl();
    		txnStatSvc.showStatisticalData(csvReportList);
    	} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Please provide CSV path as argument.");
		}
    }
}