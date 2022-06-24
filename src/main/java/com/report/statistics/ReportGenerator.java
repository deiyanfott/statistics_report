package com.report.statistics;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.txn.report.service.TransactionReportInitializerService;
import com.report.statistics.txn.report.service.TransactionReportStatisticsService;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportStatisticsServiceImpl;
import com.report.statistics.utils.validator.CSVTransactionReportValidator;

public class ReportGenerator {
    public static void main(String[] args) {
    	if (CSVTransactionReportValidator.isValidFilePath(args[0])) {
    		TransactionReportInitializerService txnReportSvc = new CSVTransactionReportInitializerServiceImpl();
    		List<TransactionReport> csvReportList = txnReportSvc.initializeData(args[0]);
    		TransactionReportStatisticsService txnStatSvc = new CSVTransactionReportStatisticsServiceImpl();
    		txnStatSvc.showStatisticalData(csvReportList);
    	}
    }
}