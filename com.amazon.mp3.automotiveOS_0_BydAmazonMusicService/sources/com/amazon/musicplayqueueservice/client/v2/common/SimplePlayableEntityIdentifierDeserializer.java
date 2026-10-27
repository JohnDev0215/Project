package com.amazon.musicplayqueueservice.client.v2.common;

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
public class SimplePlayableEntityIdentifierDeserializer extends JsonDeserializer<SimplePlayableEntityIdentifier> {
    public static final SimplePlayableEntityIdentifierDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SimplePlayableEntityIdentifierDeserializer simplePlayableEntityIdentifierDeserializer = new SimplePlayableEntityIdentifierDeserializer();
        INSTANCE = simplePlayableEntityIdentifierDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.SimplePlayableEntityIdentifierDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SimplePlayableEntityIdentifier.class, simplePlayableEntityIdentifierDeserializer);
    }

    private SimplePlayableEntityIdentifierDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SimplePlayableEntityIdentifier deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.common#SimplePlayableEntityIdentifier".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SimplePlayableEntityIdentifier deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SimplePlayableEntityIdentifier simplePlayableEntityIdentifier = new SimplePlayableEntityIdentifier();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("identifier".equals(currentName)) {
                simplePlayableEntityIdentifier.setIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("identifierType".equals(currentName)) {
                simplePlayableEntityIdentifier.setIdentifierType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return simplePlayableEntityIdentifier;
    }
}
