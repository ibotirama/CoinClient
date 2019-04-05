package com.kuehnenagel.coinclient.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.*;

/**
 * 
 * @author Pedro Andrade
 * 
 * This class is a model that wrap up the historical information from 30 days to today. 
 */
public class HistoricalRate {
	
	private List<Rate> rates;
	private Comparator<Rate> comparator;  

	public HistoricalRate(@NotNull List<Rate> rates) {
		checkNotNull(rates, "Rates can not be null.");
		this.rates = rates;
		this.comparator = Comparator.comparing(Rate::getRate);
	}
	
	public List<Rate> getPrices() {
		return Collections.unmodifiableList(rates);
	}
	
	private Rate getHighest() {
		return rates.stream().max(comparator).get();
	}

	public BigDecimal getHighestRate() {
		return this.getHighest().getRate();
	}
	
	public LocalDate getHighestDate() {
		return this.getHighest().getDate();
	}
	
	private Rate getLowerest() {
		return rates.stream().min(comparator).get();
	}
	
	public BigDecimal getLowerestRate() {
		return this.getLowerest().getRate();
	}
	
	public LocalDate getLowerestDate() {
		return this.getLowerest().getDate();
	}
	@Override
	public String toString() {
		return "HistoricalPrice [rates=" + rates + "]";
	}
	
}
