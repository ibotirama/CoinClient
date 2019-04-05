package com.kuehnenagel.coinclient.services.infra;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kuehnenagel.coinclient.exception.CurrencyNotSupportedException;
import com.kuehnenagel.coinclient.model.Constants;

/**
 * 
 * @author Pedro Andrade
 * 
 * This class is responsible to access the rest service and return the JSON with data. 
 */
@Service
public class CoinClient {
	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * @param currency The currency is the currency that we need to pass to 
	 * the rest service for return the data about the current rate of bitcoin in this currency.
	 * @return String It will return the String with the Json that contains the 
	 * bitcoin rate and other information. 
	 *
	 *  @throws NullPointerException If the given currency is <code>null</code>. 
	 *  @throws IllegalArgumentException If the given currency is empty. 
	 *  @throws CurrencyNotSupportedException If the given currency is not supported. 
	 */
	public String getCoinCurrentRate(@NotBlank String currency) {
		checkArgument(!currency.isEmpty(), "Currency can not be empty.");
		checkNotNull(currency, "Currency can not be null.");
		String data;
		try {
			data = restTemplate.getForObject(String.format(Constants.URL_CURRENT_PRICE, currency), String.class);
			return data;
		}
		catch (HttpClientErrorException.NotFound nfe){
			throw new CurrencyNotSupportedException(String.format("This currency %s is not supperted by the service CoinDesk.", currency));
		}
	}
	
	/**
	 * @param currency The currency is the currency that we need to pass to 
	 * the rest service for return the data about the current rate of bitcoin in this currency.
	 * @return String It will return the String with the Json that contains the 
	 * bitcoin rate and other information between today and 30 days before. 
	 *
	 *  @throws NullPointerException If the given currency is <code>null</code>. 
	 *  @throws IllegalArgumentException If the given currency is empty. 
	 */
	public String getHistoricalCoinRate(@NotBlank String currency) {
		checkArgument(!currency.isEmpty(), "Currency can not be empty.");
		checkNotNull(currency, "Currency can not be null.");
		
		LocalDate today = LocalDate.now();
		Period thirtyDays = Period.ofDays(30); 
		LocalDate start = today.minus(thirtyDays);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			String data = restTemplate.getForObject(String.format(Constants.URL_HISTORICAL_PRICE, start.format(formatter), today.format(formatter), currency), String.class);
			return data;
		}
		catch (HttpClientErrorException.NotFound nfe){
			throw new CurrencyNotSupportedException(String.format("This currency %s is not supperted by the service CoinDesk.", currency));
		}
	}

}
