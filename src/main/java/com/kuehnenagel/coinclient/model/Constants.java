package com.kuehnenagel.coinclient.model;

public class Constants {
	public static String URL_BASE = "https://api.coindesk.com/v1/bpi/";
	public static String URL_CURRENT_PRICE = "https://api.coindesk.com/v1/bpi/currentprice/%s.json";
	public static String URL_HISTORICAL_PRICE = "https://api.coindesk.com/v1/bpi/historical/close.json?start=%s&end=%s&currency=%s";
}
