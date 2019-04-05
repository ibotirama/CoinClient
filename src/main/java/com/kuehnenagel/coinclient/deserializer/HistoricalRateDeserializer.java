package com.kuehnenagel.coinclient.deserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kuehnenagel.coinclient.model.HistoricalRate;
import com.kuehnenagel.coinclient.model.Rate;

/**
 * @author Pedro Andrade
 * 
 * This class is responsible to know how to implement the 
 * deserialization of a Historical Rate using the Jackson framework. 
 */

@JsonDeserialize(using = HistoricalRateDeserializer.class)
public class HistoricalRateDeserializer extends StdDeserializer<HistoricalRate>{
	private static final long serialVersionUID = 2253528769252387162L;

	public HistoricalRateDeserializer(Class<?> vc) {
		super(vc);
	}

	public HistoricalRateDeserializer() {
		this(null);
	}
	
	@Override
	public HistoricalRate deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		JsonNode nodeCurrency = node.get("bpi");
		Iterator<Entry<String, JsonNode>> fields = nodeCurrency.fields();
		List<Rate> rates = new ArrayList<>();
		while(fields.hasNext()) {
			Entry<String, JsonNode> field = fields.next();
			String date = field.getKey();
			String rate = field.getValue().asText();
			String rateNormalized = rate.replace(",", "");
			Rate price = new Rate(date, new BigDecimal(rateNormalized));
			rates.add(price);
		}
		return new HistoricalRate(rates);
	}
}
