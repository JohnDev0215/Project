package com.amazon.musicsubscriptionofferservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SalesPromotionInfoDeserializer extends JsonDeserializer<SalesPromotionInfo> {
    public static final SalesPromotionInfoDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SalesPromotionInfoDeserializer salesPromotionInfoDeserializer = new SalesPromotionInfoDeserializer();
        INSTANCE = salesPromotionInfoDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.SalesPromotionInfoDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SalesPromotionInfo.class, salesPromotionInfoDeserializer);
    }

    private SalesPromotionInfoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SalesPromotionInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#SalesPromotionInfo".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SalesPromotionInfo deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SalesPromotionInfo salesPromotionInfo = new SalesPromotionInfo();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("effective".equals(currentName)) {
                salesPromotionInfo.setEffective(DateRangeDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("savingsMessage".equals(currentName)) {
                salesPromotionInfo.setSavingsMessage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("residualUses".equals(currentName)) {
                salesPromotionInfo.setResidualUses(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("subscriptionBillingMessage".equals(currentName)) {
                salesPromotionInfo.setSubscriptionBillingMessage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("expiryDate".equals(currentName)) {
                salesPromotionInfo.setExpiryDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("promotionTitle".equals(currentName)) {
                salesPromotionInfo.setPromotionTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("promotionId".equals(currentName)) {
                salesPromotionInfo.setPromotionId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return salesPromotionInfo;
    }
}
