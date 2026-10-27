package com.amazon.musicplayqueueservice.client.common;

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

/* JADX INFO: loaded from: classes5.dex */
public class GetConnectionInfoResponseDeserializer extends JsonDeserializer<GetConnectionInfoResponse> {
    public static final GetConnectionInfoResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetConnectionInfoResponseDeserializer getConnectionInfoResponseDeserializer = new GetConnectionInfoResponseDeserializer();
        INSTANCE = getConnectionInfoResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.GetConnectionInfoResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetConnectionInfoResponse.class, getConnectionInfoResponseDeserializer);
    }

    private GetConnectionInfoResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetConnectionInfoResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.common#GetConnectionInfoResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetConnectionInfoResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetConnectionInfoResponse getConnectionInfoResponse = new GetConnectionInfoResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("iotEndpoint".equals(currentName)) {
                getConnectionInfoResponse.setIotEndpoint(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("identityPoolId".equals(currentName)) {
                getConnectionInfoResponse.setIdentityPoolId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clientId".equals(currentName)) {
                getConnectionInfoResponse.setClientId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("region".equals(currentName)) {
                getConnectionInfoResponse.setRegion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getConnectionInfoResponse;
    }
}
