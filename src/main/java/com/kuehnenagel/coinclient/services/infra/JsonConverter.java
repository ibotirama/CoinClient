package com.kuehnenagel.coinclient.services.infra;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kuehnenagel.coinclient.deserializer.CurrencyDeserializer;
import com.kuehnenagel.coinclient.deserializer.HistoricalRateDeserializer;
import com.kuehnenagel.coinclient.model.Currency;
import com.kuehnenagel.coinclient.model.HistoricalRate;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Pedro Andrade
 * 
 * This class is responsible to register and deserializers the json using the Deserializers. 
 */

@Service
public class JsonConverter {
	private ObjectMapper mapper;
	private SimpleModule module;
	
	public JsonConverter() {
    	mapper = new ObjectMapper();
    	module = new SimpleModule();
    	module.addDeserializer(Currency.class, new CurrencyDeserializer());
    	module.addDeserializer(HistoricalRate.class, new HistoricalRateDeserializer());
    	mapper.registerModule(module);
	}

	/**
	 * @param data This parameter is an Json string that will used to be passed to the Deserializer.
	 * @return Currency Is the object resulting by the conversion.
	 * @throws IllegalArgumentException In case of you pass a null or empty json string.  
	 *  
	 * This method uses the Deserializer to convert the json string to a Currency object by the registerd Deserializer. 
	 */
	public Currency convertCurrencyFromJson(String data) throws JsonParseException, JsonMappingException, IOException {
		checkArgument(!data.isEmpty(), "Json data can not be empty.");
		checkNotNull(data, "Json data can not be null.");
    	return this.mapper.readValue(data, Currency.class);
	}
	
	/**
	 * @param data This parameter is an Json string that will used to be passed to the Deserializer.
	 * @return HistoricalRate Is the object resulting by the conversion.
	 * @throws IllegalArgumentException In case of you pass a null or empty json string.  
	 *  
	 * This method uses the Deserializer to convert the json string to a Historical Rate object by the registerd Deserializer.
	 */
	public HistoricalRate convertHistoricalRateFromJson(String data) throws JsonParseException, JsonMappingException, IOException {
		checkArgument(!data.isEmpty(), "Json data can not be empty.");
		checkNotNull(data, "Json data can not be null.");
		
    	return  this.mapper.readValue(data, HistoricalRate.class);
	}
}
