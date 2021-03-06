package com.coding.exam.hoolah.txn.report.service.impl.csv;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.coding.exam.hoolah.builder.CSVDataBuilderService;
import com.coding.exam.hoolah.model.base.TransactionReport;
import com.coding.exam.hoolah.model.csv.CSVTransactionReport;
import com.coding.exam.hoolah.txn.report.service.TransactionReportFilterService;
import com.coding.exam.hoolah.txn.report.service.TransactionReportInitializerService;

public class CSVTransactionReportInitializerServiceImpl implements TransactionReportInitializerService {
	@Override
	public List<TransactionReport> initializeData(String filePath) {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
			List<TransactionReport> reportList;
			Iterator<CSVTransactionReport> csvReportList = CSVDataBuilderService.buildDataFromFile(reader, CSVTransactionReport.class);
			Set<String> transactionIds = new HashSet<>();
			Set<String> relatedTransactionIds = new HashSet<>();
			
			if (csvReportList.hasNext()) {
				reportList = new LinkedList<>();
				
				while (csvReportList.hasNext()) {
	    			CSVTransactionReport csvTransactionReport = csvReportList.next();
	    			TransactionReportFilterService.getTxnAnsRelatedTxnIds(
	    					csvTransactionReport, transactionIds, relatedTransactionIds);
	    			reportList.add(csvTransactionReport);
	    		}
				
				if (CollectionUtils.isNotEmpty(relatedTransactionIds)) {
	    			transactionIds.removeAll(relatedTransactionIds);
	    			return reportList.stream().filter(csvReport -> transactionIds.contains(((CSVTransactionReport) csvReport).getTransactionId()))
	    					.collect(Collectors.toList());
	    		} else {
	    			return reportList;
	    		}
			}
		} catch (NoSuchFileException e) {
			System.err.println("Missing file in specified location.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new LinkedList<>();
	}
}