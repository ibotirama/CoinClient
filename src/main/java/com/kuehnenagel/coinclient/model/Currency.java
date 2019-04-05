package com.kuehnenagel.coinclient.model;

import java.math.BigDecimal;

/**
 * @author Pedro Henrique
 * 
 * This class is used as an model to deliver information about the current rate of
 */
public class Currency {
	
	private String currency;
	private BigDecimal rate;
	
	public Currency(String currency, BigDecimal rate) {
		this.currency = currency;
		this.rate = rate;
	}

	public String getCurrency() {
		return currency;
	}

	public BigDecimal getRate() {
		return rate;
	}

	@Override
	public String toString() {
		return "Currency [currency=" + currency + ", price=" + rate + "]";
	}

}
