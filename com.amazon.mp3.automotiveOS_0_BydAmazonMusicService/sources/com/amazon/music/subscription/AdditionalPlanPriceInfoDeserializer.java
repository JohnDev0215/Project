package com.amazon.music.subscription;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class AdditionalPlanPriceInfoDeserializer extends JsonDeserializer<AdditionalPlanPriceInfo> {
    public static final AdditionalPlanPriceInfoDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AdditionalPlanPriceInfoDeserializer additionalPlanPriceInfoDeserializer = new AdditionalPlanPriceInfoDeserializer();
        INSTANCE = additionalPlanPriceInfoDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.AdditionalPlanPriceInfoDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AdditionalPlanPriceInfo.class, additionalPlanPriceInfoDeserializer);
    }

    private AdditionalPlanPriceInfoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AdditionalPlanPriceInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.VALUE_NULL) {
            return null;
        }
        if (currentToken != JsonToken.START_OBJECT) {
            throw new JsonParseException(jsonParser, "Expected start of object, got " + currentToken, jsonParser.getTokenLocation());
        }
        if (jsonParser.nextToken() == JsonToken.FIELD_NAME && "__type".equals(jsonParser.getCurrentName())) {
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            String text = jsonParser.getText();
            jsonParser.nextToken();
            if ("com.amazon.music.subscription#AdditionalPlanPriceInfo".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AdditionalPlanPriceInfo deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AdditionalPlanPriceInfo additionalPlanPriceInfo = new AdditionalPlanPriceInfo();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("dueNowPlanPriceTotal".equals(currentName)) {
                additionalPlanPriceInfo.setDueNowPlanPriceTotal(PriceWithCurrencyDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pendingPlanPriceTotal".equals(currentName)) {
                additionalPlanPriceInfo.setPendingPlanPriceTotal(PriceWithCurrencyDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return additionalPlanPriceInfo;
    }
}
