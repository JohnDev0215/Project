package com.amazon.music.subscription;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class EvaluateSubscriptionCreationResponseDeserializer extends JsonDeserializer<EvaluateSubscriptionCreationResponse> {
    public static final EvaluateSubscriptionCreationResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        EvaluateSubscriptionCreationResponseDeserializer evaluateSubscriptionCreationResponseDeserializer = new EvaluateSubscriptionCreationResponseDeserializer();
        INSTANCE = evaluateSubscriptionCreationResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.subscription.EvaluateSubscriptionCreationResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(EvaluateSubscriptionCreationResponse.class, evaluateSubscriptionCreationResponseDeserializer);
    }

    private EvaluateSubscriptionCreationResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public EvaluateSubscriptionCreationResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.subscription#EvaluateSubscriptionCreationResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public EvaluateSubscriptionCreationResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        EvaluateSubscriptionCreationResponse evaluateSubscriptionCreationResponse = new EvaluateSubscriptionCreationResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("orderInitToken".equals(currentName)) {
                evaluateSubscriptionCreationResponse.setOrderInitToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("purchaseHandleId".equals(currentName)) {
                evaluateSubscriptionCreationResponse.setPurchaseHandleId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return evaluateSubscriptionCreationResponse;
    }
}
