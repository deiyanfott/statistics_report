package com.coding.exam.hoolah.txn.report.service.impl.csv.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.txn.report.service.TransactionReportInitializerService;
import com.coding.exam.hoolah.txn.report.service.impl.csv.CSVTransactionReportInitializerServiceImpl;

public class CSVTransactionReportInitializerServiceImplTest {
	private TransactionReportInitializerService testService = new CSVTransactionReportInitializerServiceImpl();
	
	@Test
	public void testPathFileIsCorrect() {
		List<TransactionReport> reportList = testService.initializeData("C:/temp/sample.csv");
		assertEquals(true, CollectionUtils.isNotEmpty(reportList));
	}
	
	@Test
	public void testPathFileIsIncorrect() {
		List<TransactionReport> reportList = testService.initializeData("C:/temp/test.csv");
		assertEquals(true, CollectionUtils.isEmpty(reportList));
	}
	
	@Test
	public void testFileHasReversals() {
		List<TransactionReport> reportList = testService.initializeData("C:/temp/sample.csv");
		assertEquals(4, reportList.size());
	}
	
	@Test
	public void testFileHasNoReversals() {
		List<TransactionReport> reportList = testService.initializeData("C:/temp/sample2.csv");
		assertEquals(6, reportList.size());
	}
}