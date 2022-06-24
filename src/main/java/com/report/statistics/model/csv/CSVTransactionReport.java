package com.report.statistics.model.csv;

import java.math.BigDecimal;
import java.util.Date;

import com.report.statistics.model.base.TransactionReport;
import com.report.statistics.utils.converter.csv.CSVAmountConverter;
import com.report.statistics.utils.converter.csv.CSVDateConverter;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(includeFieldNames = true)
public class CSVTransactionReport extends TransactionReport {
	@CsvBindByPosition(position = 0, required = true)
	private String transactionId;
	
	@CsvCustomBindByPosition(position = 1, required = true, converter = CSVDateConverter.class)
	@CsvDate("dd/MM/yyyy HH:mm:ss")
	private Date date;
	
	@CsvCustomBindByPosition(position = 2, required = true, converter = CSVAmountConverter.class)
	@CsvNumber("#.##")
	private BigDecimal amount;
	
	@CsvBindByPosition(position = 3, required = true)
	private String merchant;
	
	@CsvBindByPosition(position = 4, required = true)
	private String transactionType;
	
	@CsvBindByPosition(position = 5)
	private String relatedTransactionId;
}