package com.coding.exam.hoolah.txn.report.service.impl.csv;

import java.util.List;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.model.csv.CSVTransactionReport;
import com.coding.exam.hoolah.txn.report.service.TransactionReportFilterService;

public class CSVTransactionReportFilterServiceImpl implements TransactionReportFilterService {
	@Override
	public BigDecimalSummaryStatistics filterStatisticalData(
			List<TransactionReport> csvReportList,
			CSVInputParameters csvInputParameters) {
		return csvReportList.stream().filter(csvRptTxn ->
				csvInputParameters.getMerchant().equals(((CSVTransactionReport) csvRptTxn).getMerchant().trim()) &&
				csvInputParameters.getStartDate().compareTo(((CSVTransactionReport) csvRptTxn).getDate()) <= 0 &&
				csvInputParameters.getEndDate().compareTo(((CSVTransactionReport) csvRptTxn).getDate()) >= 0)
				.collect(Collectors2.summarizingBigDecimal(csvRptTxn -> ((CSVTransactionReport) csvRptTxn).getAmount()));
	}
}