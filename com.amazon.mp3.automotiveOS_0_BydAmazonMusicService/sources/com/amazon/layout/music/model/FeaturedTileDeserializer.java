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
public class FeaturedTileDeserializer extends JsonDeserializer<FeaturedTile> {
    public static final FeaturedTileDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedTileDeserializer featuredTileDeserializer = new FeaturedTileDeserializer();
        INSTANCE = featuredTileDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.FeaturedTileDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FeaturedTile.class, featuredTileDeserializer);
    }

    private FeaturedTileDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FeaturedTile deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#FeaturedTile".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FeaturedTile deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FeaturedTile featuredTile = new FeaturedTile();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.IMAGE.equals(currentName)) {
                featuredTile.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                featuredTile.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                featuredTile.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subHeader".equals(currentName)) {
                featuredTile.setSubHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                featuredTile.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("logo".equals(currentName)) {
                featuredTile.setLogo(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("text".equals(currentName)) {
                featuredTile.setText(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                featuredTile.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hint".equals(currentName)) {
                featuredTile.setHint(HintDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tileTarget".equals(currentName)) {
                featuredTile.setTileTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return featuredTile;
    }
}
