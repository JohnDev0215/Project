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
public class VerticalTileDeserializer extends JsonDeserializer<VerticalTile> {
    public static final VerticalTileDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        VerticalTileDeserializer verticalTileDeserializer = new VerticalTileDeserializer();
        INSTANCE = verticalTileDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.VerticalTileDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(VerticalTile.class, verticalTileDeserializer);
    }

    private VerticalTileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public VerticalTile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#VerticalTile".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public VerticalTile deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        VerticalTile verticalTile = new VerticalTile();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("subTitle".equals(currentName)) {
                verticalTile.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                verticalTile.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                verticalTile.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subTitleTarget".equals(currentName)) {
                verticalTile.setSubTitleTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playbackTarget".equals(currentName)) {
                verticalTile.setPlaybackTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                verticalTile.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("titleTarget".equals(currentName)) {
                verticalTile.setTitleTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hint".equals(currentName)) {
                verticalTile.setHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                verticalTile.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tileTarget".equals(currentName)) {
                verticalTile.setTileTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return verticalTile;
    }
}
