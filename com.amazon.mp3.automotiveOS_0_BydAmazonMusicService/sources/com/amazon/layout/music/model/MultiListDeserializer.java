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
public class MultiListDeserializer extends JsonDeserializer<MultiList> {
    public static final MultiListDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MultiListDeserializer multiListDeserializer = new MultiListDeserializer();
        INSTANCE = multiListDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.MultiListDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MultiList.class, multiListDeserializer);
    }

    private MultiListDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MultiList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#MultiList".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MultiList deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MultiList multiList = new MultiList();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("list2".equals(currentName)) {
                multiList.setList2(SingleListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("list1".equals(currentName)) {
                multiList.setList1(SingleListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                multiList.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blocks".equals(currentName)) {
                multiList.setBlocks(BlockListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return multiList;
    }
}
