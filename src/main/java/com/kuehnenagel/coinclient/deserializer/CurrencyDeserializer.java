package com.kuehnenagel.coinclient.deserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kuehnenagel.coinclient.model.Currency;

/**
 * @author Pedro Andrade
 * 
 * This class is responsible to know how to implement the 
 * deserialization of a Currency using the Jackson framework. 
 */
@JsonDeserialize(using = CurrencyDeserializer.class)
public class CurrencyDeserializer extends StdDeserializer<Currency>{
	private static final long serialVersionUID = -3253985567108863771L;
	
	public CurrencyDeserializer(Class<?> vc) {
		super(vc);
	}

	public CurrencyDeserializer() {
		this(null);
	}
	
	@Override
	public Currency deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode nodeCurrency = node.get("bpi");
		Iterator<Entry<String, JsonNode>> fields = nodeCurrency.fields();
		while(fields.hasNext()) {
			Entry<String, JsonNode> field = fields.next();
			if(!field.getKey().equals("USD")) {
				String name = field.getValue().get("code").asText();
				String rate = field.getValue().get("rate").asText();
				String rateNormalized = rate.replace(",", "");
				return new Currency(name, new BigDecimal(rateNormalized));
			}
		}
		
		return null;
	}
}
