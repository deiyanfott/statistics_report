package com.report.statistics.txn.report.service.impl.csv;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.model.csv.CSVInputParameters;
import com.report.statistics.model.csv.CSVTransactionReport;
import com.report.statistics.txn.report.service.TransactionReportFilterService;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;

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