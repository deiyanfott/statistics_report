package com.coding.exam.hoolah.txn.report.service;

import java.util.List;
import java.util.Set;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;

import com.coding.exam.hoolah.enums.TransactionType;
import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.model.csv.CSVTransactionReport;

public interface TransactionReportFilterService {
	public static void getTxnAnsRelatedTxnIds(
			CSVTransactionReport csvTransactionReport,
			Set<String> transactionIdSet,
			Set<String> relatedTransactionIds) {
		
		if (TransactionType.REVERSAL.toString().equals(csvTransactionReport.getTransactionType().trim())) {
			relatedTransactionIds.add(csvTransactionReport.getRelatedTransactionId().trim());
		} else if (TransactionType.PAYMENT.toString().equals(csvTransactionReport.getTransactionType().trim())) {
			transactionIdSet.add(csvTransactionReport.getTransactionId());
		}
	}
	
	public abstract BigDecimalSummaryStatistics filterStatisticalData(
			List<TransactionReport> csvReportList,
			CSVInputParameters csvInputParameters);
}