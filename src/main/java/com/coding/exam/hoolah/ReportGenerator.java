package com.coding.exam.hoolah;

import java.util.List;

import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.txn.report.service.TransactionReportInitializerService;
import com.coding.exam.hoolah.txn.report.service.TransactionReportStatisticsService;
import com.coding.exam.hoolah.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;
import com.coding.exam.hoolah.txn.report.service.impl.csv.CSVTransactionReportStatisticsServiceImpl;
import com.coding.exam.hoolah.utils.validator.CSVTransactionReportValidator;

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