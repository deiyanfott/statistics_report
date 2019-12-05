package com.coding.exam.hoolah.builder;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.junit.Test;

import com.coding.exam.hoolah.builder.CSVDataBuilderService;
import com.coding.exam.hoolah.model.csv.CSVTransactionReport;

public class CSVDataBuilderTest {
	@Test
	public void testPathFileIsCorrect() {
		try (Reader reader = Files.newBufferedReader(Paths.get("C:/temp/sample.csv"));) {
			Iterator<CSVTransactionReport> iterator = CSVDataBuilderService.buildDataFromFile(reader, CSVTransactionReport.class);
			assertEquals(true, iterator.hasNext());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPathFileIsIncorrect() {
		try (Reader reader = Files.newBufferedReader(Paths.get("C:/temp/test.csv"));) {
			Iterator<CSVTransactionReport> iterator = CSVDataBuilderService.buildDataFromFile(reader, CSVTransactionReport.class);
			assertEquals(false, iterator.hasNext());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}