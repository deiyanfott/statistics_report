package com.coding.exam.hoolah.builder;

import java.io.Reader;
import java.util.Iterator;

import com.coding.exam.hoolah.model.csv.CSVTransactionReport;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVDataBuilderService {
	private CSVDataBuilderService() {
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Iterator<CSVTransactionReport> buildDataFromFile(Reader reader, Class<?> clazz) {
		ColumnPositionMappingStrategy ms = new ColumnPositionMappingStrategy<>();
		ms.setType(clazz);
		
		return new CsvToBeanBuilder(reader)
				.withSkipLines(1)
				.withType(clazz)
				.withMappingStrategy(ms)
				.build().iterator();
	}
}