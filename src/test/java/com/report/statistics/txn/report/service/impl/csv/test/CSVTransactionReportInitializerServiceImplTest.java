package com.report.statistics.txn.report.service.impl.csv.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.txn.report.service.TransactionReportInitializerService;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.report.statistics.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;

public class CSVTransactionReportInitializerServiceImplTest {
	private TransactionReportInitializerService testService = new CSVTransactionReportInitializerServiceImpl();
	
	@Test
	public void testPathFileIsCorrect() {
		List<TransactionReport> reportList = testService.initializeData("src/test/resources/TestFile.csv");
		assertEquals(true, CollectionUtils.isNotEmpty(reportList));
	}
	
	@Test
	public void testPathFileIsIncorrect() {
		List<TransactionReport> reportList = testService.initializeData("src/test/resources/NoSuchFile.csv");
		assertEquals(true, CollectionUtils.isEmpty(reportList));
	}
	
	@Test
	public void testFileHasReversals() {
		List<TransactionReport> reportList = testService.initializeData("src/test/resources/TestFile.csv");
		assertEquals(4, reportList.size());
	}
	
	@Test
	public void testFileHasNoReversals() {
		List<TransactionReport> reportList = testService.initializeData("src/test/resources/TestFileNoReversals.csv");
		assertEquals(6, reportList.size());
	}
}