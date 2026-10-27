package com.amazon.stratus;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class BenefitMapDeserializer extends JsonDeserializer<Map<String, CustomerBenefit>> {
    public static final JsonDeserializer<Map<String, CustomerBenefit>> INSTANCE = new BenefitMapDeserializer();

    private BenefitMapDeserializer() {
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Map<String, CustomerBenefit> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of map, got " + currentToken, jsonParser.getTokenLocation());
        }
        HashMap map = new HashMap();
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + currentToken, jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            map.put(currentName, CustomerBenefitDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
        }
        return map;
    }
}
