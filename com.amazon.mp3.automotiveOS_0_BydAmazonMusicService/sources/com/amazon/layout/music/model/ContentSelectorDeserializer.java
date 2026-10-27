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
public class ContentSelectorDeserializer extends JsonDeserializer<ContentSelector> {
    public static final ContentSelectorDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ContentSelectorDeserializer contentSelectorDeserializer = new ContentSelectorDeserializer();
        INSTANCE = contentSelectorDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.ContentSelectorDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ContentSelector.class, contentSelectorDeserializer);
    }

    private ContentSelectorDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ContentSelector deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#ContentSelector".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ContentSelector deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ContentSelector contentSelector = new ContentSelector();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("shouldDisplaySelectors".equals(currentName)) {
                contentSelector.setShouldDisplaySelectors(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("positionSelector".equals(currentName)) {
                contentSelector.setPositionSelector(PositionSelectorDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("colorDecorator".equals(currentName)) {
                contentSelector.setColorDecorator(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                contentSelector.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("filterSelector".equals(currentName)) {
                contentSelector.setFilterSelector(FilterSelectorDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blocks".equals(currentName)) {
                contentSelector.setBlocks(BlockListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return contentSelector;
    }
}
