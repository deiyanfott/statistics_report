package com.coding.exam.hoolah.txn.report.service.impl.csv.test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.junit.Test;

import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.model.csv.CSVInputParameters;
import com.coding.exam.hoolah.txn.report.service.TransactionReportFilterService;
import com.coding.exam.hoolah.txn.report.service.TransactionReportInitializerService;
import com.coding.exam.hoolah.txn.report.service.impl.csv.CSVTransactionReportFilterServiceImpl;
import com.coding.exam.hoolah.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;
import com.coding.exam.hoolah.utils.validator.CSVTransactionReportValidator;

public class CSVTransactionReportFilterServiceImplTest {
	private TransactionReportInitializerService testIntializerService = new CSVTransactionReportInitializerServiceImpl();
	private TransactionReportFilterService testFilterService = new CSVTransactionReportFilterServiceImpl();

	@Test
	public void testSampleCSV() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		List<TransactionReport> reportList = testIntializerService.initializeData("C:/temp/sample.csv");
		BigDecimalSummaryStatistics statistics = testFilterService.filterStatisticalData(reportList, params);
		
		assertEquals(1, statistics.getCount());
		assertEquals(BigDecimal.valueOf(59.99), statistics.getAverage());
	}

	@Test
	public void testSample2CSV() {
		CSVInputParameters params = new CSVInputParameters();
		params.setDateFrom("20/08/2018 12:00:00");
		params.setDateTo("20/08/2018 13:00:00");
		params.setMerchant("Kwik-E-Mart");
		
		CSVTransactionReportValidator.validateAndSetInputParameters(params);
		List<TransactionReport> reportList = testIntializerService.initializeData("C:/temp/sample2.csv");
		BigDecimalSummaryStatistics statistics = testFilterService.filterStatisticalData(reportList, params);
		
		assertEquals(2, statistics.getCount());
		assertEquals(BigDecimal.valueOf(35.47), statistics.getAverage());
	}
}
