package com.amazon.musicplayqueueservice.client.v2.common;

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
public class AbstractPlayableEntityIdentifierDeserializer extends JsonDeserializer<AbstractPlayableEntityIdentifier> {
    public static final AbstractPlayableEntityIdentifierDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AbstractPlayableEntityIdentifierDeserializer abstractPlayableEntityIdentifierDeserializer = new AbstractPlayableEntityIdentifierDeserializer();
        INSTANCE = abstractPlayableEntityIdentifierDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifierDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AbstractPlayableEntityIdentifier.class, abstractPlayableEntityIdentifierDeserializer);
    }

    private AbstractPlayableEntityIdentifierDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AbstractPlayableEntityIdentifier deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return SimplePlayableEntityIdentifierDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplayqueueservice.client.v2.common#LibraryAlbumIdentifier".equals(text)) {
                return LibraryAlbumIdentifierDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.musicplayqueueservice.client.v2.common#AbstractPlayableEntityIdentifier".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AbstractPlayableEntityIdentifier deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
