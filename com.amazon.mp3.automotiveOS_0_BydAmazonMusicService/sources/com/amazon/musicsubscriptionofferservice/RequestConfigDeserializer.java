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
public class RequestConfigDeserializer extends JsonDeserializer<RequestConfig> {
    public static final RequestConfigDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RequestConfigDeserializer requestConfigDeserializer = new RequestConfigDeserializer();
        INSTANCE = requestConfigDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicsubscriptionofferservice.RequestConfigDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RequestConfig.class, requestConfigDeserializer);
    }

    private RequestConfigDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RequestConfig deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicsubscriptionofferservice#RequestConfig".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RequestConfig deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RequestConfig requestConfig = new RequestConfig();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("includeBuyability".equals(currentName)) {
                requestConfig.setIncludeBuyability(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("alternateDevices".equals(currentName)) {
                requestConfig.setAlternateDevices(AlternateDevicesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("includeOfferMetadata".equals(currentName)) {
                requestConfig.setIncludeOfferMetadata(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("includeIneligibleOffers".equals(currentName)) {
                requestConfig.setIncludeIneligibleOffers(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return requestConfig;
    }
}
