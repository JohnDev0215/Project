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
public class RetrieveOwnedContentCapabilityDeserializer extends JsonDeserializer<RetrieveOwnedContentCapability> {
    public static final RetrieveOwnedContentCapabilityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RetrieveOwnedContentCapabilityDeserializer retrieveOwnedContentCapabilityDeserializer = new RetrieveOwnedContentCapabilityDeserializer();
        INSTANCE = retrieveOwnedContentCapabilityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.stratus.RetrieveOwnedContentCapabilityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RetrieveOwnedContentCapability.class, retrieveOwnedContentCapabilityDeserializer);
    }

    private RetrieveOwnedContentCapabilityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RetrieveOwnedContentCapability deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.stratus#RetrieveOwnedContentCapability".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RetrieveOwnedContentCapability deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RetrieveOwnedContentCapability retrieveOwnedContentCapability = new RetrieveOwnedContentCapability();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("capability".equals(currentName)) {
                retrieveOwnedContentCapability.setCapability(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("maxGrantsPerCustomer".equals(currentName)) {
                retrieveOwnedContentCapability.setMaxGrantsPerCustomer(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("validUntil".equals(currentName)) {
                retrieveOwnedContentCapability.setValidUntil(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return retrieveOwnedContentCapability;
    }
}
