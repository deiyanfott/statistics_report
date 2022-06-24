package com.report.statistics.txn.report.service.impl.csv.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.model.csv.CSVInputParameters;
import com.report.statistics.txn.report.service.TransactionReportFilterService;
import com.report.statistics.txn.report.service.TransactionReportInitializerService;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportFilterServiceImpl;
import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;
import com.report.statistics.utils.validator.CSVTransactionReportValidator;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.junit.Test;

public class CSVTransactionReportFilterServiceImplTest {
	private TransactionReportInitializerService testInitializerService = new CSVTransactionReportInitializerServiceImpl();
	private TransactionReportFilterService testFilterService = new CSVTransactionReportFilterServiceImpl();

	@Test
	public void testTestFileCSV() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2022");
		params.setDateTo("20/08/2022");
		params.setMerchant("Kwik-E-Mart");

		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		List<TransactionReport> reportList = testInitializerService.initializeData("src/test/resources/TestFile.csv");
		BigDecimalSummaryStatistics statistics = testFilterService.filterStatisticalData(reportList, params);
		
		assertEquals(2, statistics.getCount());
		assertEquals(BigDecimal.valueOf(32.49), statistics.getAverage().setScale(2, RoundingMode.DOWN));
	}

	@Test
	public void testTestFileNoReversalsCSV() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2022 12:00:00");
		params.setDateTo("20/08/2022 13:00:00");
		params.setMerchant("Kwik-E-Mart");

		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		List<TransactionReport> reportList = testInitializerService.initializeData("src/test/resources/TestFileNoReversals.csv");
		BigDecimalSummaryStatistics statistics = testFilterService.filterStatisticalData(reportList, params);
		
		assertEquals(2, statistics.getCount());
		assertEquals(BigDecimal.valueOf(35.47), statistics.getAverage().setScale(2, RoundingMode.DOWN));
	}
}
