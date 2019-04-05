package com.kuehnenagel.coinclient.services;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kuehnenagel.coinclient.model.Currency;
import com.kuehnenagel.coinclient.model.HistoricalRate;
import com.kuehnenagel.coinclient.services.infra.CoinClient;
import com.kuehnenagel.coinclient.services.infra.JsonConverter;

/**
 * @author Pedro Andrade
 * 
 * This class is responsible encapsulate the access to the rest service and and data conversion. 
 */
@Service
public class CoinService {

	@Autowired
	private CoinClient coinClient;
	@Autowired
	private JsonConverter converter;

	/**
	 * @param currencyStr Is the name of the currency that we need to research in the CoinDesk Service.
	 * @return Currency Instance of the object that contain the given currency and the current bitcoin rate.
	 * 
	 *  @throws NullPointerException In case of currency is passed as <code>null</code>.
	 *  @throws IllegalArgumentException In case of currency is passed as blank.
	 */
	public Currency getCoinCurrentRate(@NotBlank String currencyStr) {
		checkArgument(!currencyStr.isEmpty(), "Currency can not be empty.");
		checkNotNull(currencyStr, "Currency can not be null.");
		Currency currency = null;
		try {
			String data = coinClient.getCoinCurrentRate(currencyStr);
			currency = converter.convertCurrencyFromJson(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return currency;
	}

	/**
	 * @param currencyStr Is the name of the currency that we need to research in the CoinDesk Service.
	 * @return HistoricalRate of the object that contain list of rates in the last 30 days 
	 * and return the highest and lowerest rate.
	 * 
	 *  @throws NullPointerException In case of currency is passed as <code>null</code>.
	 *  @throws IllegalArgumentException In case of currency is passed as blank.
	 */
	public HistoricalRate getHistoricalCoinRate(String currencyStr) {
		checkArgument(!currencyStr.isEmpty(), "Currency can not be empty.");
		checkNotNull(currencyStr, "Currency can not be null.");
		HistoricalRate historicalRate = null;
		try {
			String data = coinClient.getHistoricalCoinRate(currencyStr);
			historicalRate = converter.convertHistoricalRateFromJson(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return historicalRate;
	}
	
	
	
}
