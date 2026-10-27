package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class RowItemDeserializer extends JsonDeserializer<RowItem> {
    public static final RowItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RowItemDeserializer rowItemDeserializer = new RowItemDeserializer();
        INSTANCE = rowItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.RowItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RowItem.class, rowItemDeserializer);
    }

    private RowItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RowItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#RowItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RowItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RowItem rowItem = new RowItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title1Target".equals(currentName)) {
                rowItem.setTitle1Target(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rowItemTarget".equals(currentName)) {
                rowItem.setRowItemTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                rowItem.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title2Target".equals(currentName)) {
                rowItem.setTitle2Target(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hint".equals(currentName)) {
                rowItem.setHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title2".equals(currentName)) {
                rowItem.setTitle2(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("leftText".equals(currentName)) {
                rowItem.setLeftText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title1".equals(currentName)) {
                rowItem.setTitle1(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                rowItem.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return rowItem;
    }
}
