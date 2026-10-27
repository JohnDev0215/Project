package com.amazon.stratus;

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
public class PaymentsPreferenceKeyDeserializer extends JsonDeserializer<PaymentsPreferenceKey> {
    public static final PaymentsPreferenceKeyDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PaymentsPreferenceKeyDeserializer paymentsPreferenceKeyDeserializer = new PaymentsPreferenceKeyDeserializer();
        INSTANCE = paymentsPreferenceKeyDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.PaymentsPreferenceKeyDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PaymentsPreferenceKey.class, paymentsPreferenceKeyDeserializer);
    }

    private PaymentsPreferenceKeyDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PaymentsPreferenceKey deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#PaymentsPreferenceKey".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PaymentsPreferenceKey deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PaymentsPreferenceKey paymentsPreferenceKey = new PaymentsPreferenceKey();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("marketplaceId".equals(currentName)) {
                paymentsPreferenceKey.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("externalReferenceId".equals(currentName)) {
                paymentsPreferenceKey.setExternalReferenceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("paymentMethodId".equals(currentName)) {
                paymentsPreferenceKey.setPaymentMethodId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("customerId".equals(currentName)) {
                paymentsPreferenceKey.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("paymentPreferenceId".equals(currentName)) {
                paymentsPreferenceKey.setPaymentPreferenceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("preferenceType".equals(currentName)) {
                paymentsPreferenceKey.setPreferenceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return paymentsPreferenceKey;
    }
}
