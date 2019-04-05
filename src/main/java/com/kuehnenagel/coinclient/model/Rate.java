package com.kuehnenagel.coinclient.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rate {
	private LocalDate date;
	private BigDecimal rate;
	
	public Rate(String date, BigDecimal rate) {
		LocalDate dateParsed = LocalDate.parse(date);
		this.date = dateParsed;
		this.rate = rate;
	}

	public LocalDate getDate() {
		return date;
	}

	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "Price [date=" + date + ", rate=" + rate + "]";
	}
	
}
