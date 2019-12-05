package com.coding.exam.hoolah.txn.report.service;

import java.util.List;

import com.coding.exam.hoolah.model.base.TransactionReport;

public interface TransactionReportInitializerService {
	public List<TransactionReport> initializeData(String filePath);
}