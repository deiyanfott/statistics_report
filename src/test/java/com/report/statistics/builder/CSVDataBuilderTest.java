package com.report.statistics.builder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;

import org.junit.Test;

import com.report.statistics.model.csv.CSVTransactionReport;

public class CSVDataBuilderTest {
	@Test
	public void testPathFileIsCorrect() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("TestFile.csv");
			    Reader reader = new InputStreamReader(inputStream)) {
			Iterator<CSVTransactionReport> iterator = CSVDataBuilderService.buildDataFromFile(reader, CSVTransactionReport.class);
			assertEquals(true, iterator.hasNext());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected = NullPointerException.class)
	public void testPathFileIsIncorrect() {
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("NoSuchFile.csv");
			    Reader reader = new InputStreamReader(inputStream)) {
			Iterator<CSVTransactionReport> iterator = CSVDataBuilderService.buildDataFromFile(reader, CSVTransactionReport.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}