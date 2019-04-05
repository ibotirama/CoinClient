package com.kuehnenagel.coinclient;

import com.kuehnenagel.coinclient.exception.CurrencyNotSupportedException;
import com.kuehnenagel.coinclient.model.Currency;
import com.kuehnenagel.coinclient.model.HistoricalRate;
import com.kuehnenagel.coinclient.services.CoinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 
 * @author Pedro Andrade
 * 
 * This class is responsible for get information from the user and access the 
 * Service Layer for shows informaion about the Bitcoin rate.
 * 
 */
@Component
public class CoinConsole {
	@Autowired
	private CoinService coinService;
	
    private final Logger logger = LoggerFactory.getLogger(CoinConsole.class);
   
    /**
     * This method performs the action to read the keyboard and show the data in the console.
	 * @param arg
	 */
	public void perform(String currencyStr) {
	        try {
	        	System.out.println("Researching on the internet...");
	        	String currencyUpper = currencyStr.toUpperCase();
	        	Currency currency = coinService.getCoinCurrentRate(currencyUpper);
	        	HistoricalRate historical = coinService.getHistoricalCoinRate(currencyUpper);
	        	
	        	String stringToPresent = this.getPresentationString(currency, historical);
	        	System.out.println(stringToPresent);
			} catch (CurrencyNotSupportedException e) {
				logger.error(e.getMessage());
			}
	}

    /**
     * @param Currency This is the model that represents the current rate of bitcoin in one currency.
     * @param HistoricalRate This is the model that represents the list of rates in 30 days and 
     * returns the highest and lowerest in this term.
     *  
     * This method return the string to be presented in the console.
     * 
     * @throws IllegalArgumentException In case of passing one of the arguments as null.
     */
	private String getPresentationString(Currency currency, HistoricalRate historical) {
		checkNotNull(currency, "Currency can not be null.");
		checkNotNull(historical, "Historical Rate can not be null.");
		
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Current Currency is......................: " + currency.getCurrency());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append("Current Bitcoin rate is..................: " + currency.getRate());
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(String.format("Lowest Bitcoin rate from 30 days.........: %f in %s", historical.getLowerestRate(), historical.getLowerestDate()));
		stringBuilder.append(System.getProperty("line.separator"));
		stringBuilder.append(String.format("Highest Bitcoin rate from 30 days........: %f in %s", historical.getHighestRate(), historical.getHighestDate()));
		return stringBuilder.toString();
	}
}
