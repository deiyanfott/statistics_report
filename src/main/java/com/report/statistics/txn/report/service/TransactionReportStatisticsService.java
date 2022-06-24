package com.report.statistics.txn.report.service;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;

public interface TransactionReportStatisticsService {
	public void showStatisticalData(List<TransactionReport> csvReportList);
}