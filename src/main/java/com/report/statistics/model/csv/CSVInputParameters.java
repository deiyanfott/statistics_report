package com.report.statistics.model.csv;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class CSVInputParameters {
	private String dateFrom;
	private String dateTo;
	private String merchant;
	private boolean isValidated = false;
	private Date startDate;
	private Date endDate;
}