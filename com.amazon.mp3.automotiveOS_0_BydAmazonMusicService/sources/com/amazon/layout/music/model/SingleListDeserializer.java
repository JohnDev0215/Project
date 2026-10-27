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
public class SingleListDeserializer extends JsonDeserializer<SingleList> {
    public static final SingleListDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SingleListDeserializer singleListDeserializer = new SingleListDeserializer();
        INSTANCE = singleListDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.SingleListDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SingleList.class, singleListDeserializer);
    }

    private SingleListDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SingleList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#SingleList".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SingleList deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SingleList singleList = new SingleList();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("moreText".equals(currentName)) {
                singleList.setMoreText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                singleList.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("moreTarget".equals(currentName)) {
                singleList.setMoreTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                singleList.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                singleList.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                singleList.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blocks".equals(currentName)) {
                singleList.setBlocks(BlockListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return singleList;
    }
}
