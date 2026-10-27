package com.amazon.music.storeservice.model;

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

/* JADX INFO: loaded from: classes4.dex */
public class PurchaseDeserializer extends JsonDeserializer<Purchase> {
    public static final PurchaseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PurchaseDeserializer purchaseDeserializer = new PurchaseDeserializer();
        INSTANCE = purchaseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.PurchaseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Purchase.class, purchaseDeserializer);
    }

    private PurchaseDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Purchase deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#Purchase".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Purchase deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Purchase purchase = new Purchase();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("currency".equals(currentName)) {
                purchase.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deliveryRestrictions".equals(currentName)) {
                purchase.setDeliveryRestrictions(DeliveryRestrictionsListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                purchase.setPrice(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("owned".equals(currentName)) {
                purchase.setOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return purchase;
    }
}
