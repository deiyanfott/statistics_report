package com.report.statistics.txn.report.service;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;

public interface TransactionReportInitializerService {
	public List<TransactionReport> initializeData(String filePath);
}