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
public class PeekTileDeserializer extends JsonDeserializer<PeekTile> {
    public static final PeekTileDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PeekTileDeserializer peekTileDeserializer = new PeekTileDeserializer();
        INSTANCE = peekTileDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.PeekTileDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PeekTile.class, peekTileDeserializer);
    }

    private PeekTileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PeekTile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#PeekTile".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PeekTile deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PeekTile peekTile = new PeekTile();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("imageTitleTarget".equals(currentName)) {
                peekTile.setImageTitleTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageTitle".equals(currentName)) {
                peekTile.setImageTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageTarget2".equals(currentName)) {
                peekTile.setImageTarget2(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hint".equals(currentName)) {
                peekTile.setHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("imageTarget1".equals(currentName)) {
                peekTile.setImageTarget1(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageSubtitle1".equals(currentName)) {
                peekTile.setImageSubtitle1(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageSubtitle2".equals(currentName)) {
                peekTile.setImageSubtitle2(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                peekTile.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                peekTile.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return peekTile;
    }
}
