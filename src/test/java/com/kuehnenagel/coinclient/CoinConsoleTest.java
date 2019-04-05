package com.kuehnenagel.coinclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import com.kuehnenagel.coinclient.services.infra.*;

public class CoinConsoleTest {

	/**
	 * This test ensures that we can not pass <code>null</code> as parameter to	
	 *  <code>CoinClient().getCoinCurrentPrice(currency)</code> 
	 */
	@Test
	public void testGetCoinCurrentRateReceiveNull() {
		CoinClient infra = new CoinClient();
		try {
			infra.getCoinCurrentRate(null);
			fail("Currency can not be null.");
		}
		catch (NullPointerException ne) {
		}
	}

	/**
	 * This test ensures that we can not pass an empty String as parameter to	
	 *  <code>CoinClient().getCoinCurrentPrice(currency)</code> 
	 */
	@Test
	public void testGetCoinCurrentRateReceiveEmpty() {
		CoinClient client = new CoinClient();
		try {
			client.getCoinCurrentRate("");
		}
		catch(Exception ex) {
			 assertThat(ex).isInstanceOf(IllegalArgumentException.class);
			 assertThat(ex).hasMessage("Currency can not be empty.");
		}
		
	}
	
	/**
	 * This test ensures that we can not pass <code>null</code> as parameter to	
	 *  <code>CoinClient().getHistoricalCoinRate(currency)</code> 
	 */
	@Test
	public void testGetHistoricalCoinRateReceiveNull() {
		CoinClient client = new CoinClient();
		try {
			client.getHistoricalCoinRate(null);
			fail("Currency can not be null.");
		}
		catch (NullPointerException ne) {
		}
	}

	/**
	 * This test ensures that we can not pass an empty String as parameter to	
	 *  <code>CoinClient().getHistoricalCoinRate(currency)</code> 
	 */
	@Test
	public void testGetHistoricalCoinRateReceiveEmpty() {
		CoinClient client = new CoinClient();
		try {
			client.getHistoricalCoinRate("");
		}
		catch(Exception ex) {
			 assertThat(ex).isInstanceOf(IllegalArgumentException.class);
			 assertThat(ex).hasMessage("Currency can not be empty.");
		}
		
	}

}
