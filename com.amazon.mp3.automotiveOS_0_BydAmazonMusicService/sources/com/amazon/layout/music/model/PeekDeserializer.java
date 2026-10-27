package com.amazon.layout.music.model;

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

/* JADX INFO: loaded from: classes3.dex */
public class PeekDeserializer extends JsonDeserializer<Peek> {
    public static final PeekDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PeekDeserializer peekDeserializer = new PeekDeserializer();
        INSTANCE = peekDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.PeekDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Peek.class, peekDeserializer);
    }

    private PeekDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Peek deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#Peek".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Peek deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Peek peek = new Peek();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("subTitle".equals(currentName)) {
                peek.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tile".equals(currentName)) {
                peek.setTile(BlockDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                peek.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("expandText".equals(currentName)) {
                peek.setExpandText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("expandTextTarget".equals(currentName)) {
                peek.setExpandTextTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("moreText".equals(currentName)) {
                peek.setMoreText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                peek.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("moreTarget".equals(currentName)) {
                peek.setMoreTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blocks".equals(currentName)) {
                peek.setBlocks(BlockListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return peek;
    }
}
