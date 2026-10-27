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
public class FeaturedBarkerDeserializer extends JsonDeserializer<FeaturedBarker> {
    public static final FeaturedBarkerDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedBarkerDeserializer featuredBarkerDeserializer = new FeaturedBarkerDeserializer();
        INSTANCE = featuredBarkerDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.FeaturedBarkerDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FeaturedBarker.class, featuredBarkerDeserializer);
    }

    private FeaturedBarkerDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FeaturedBarker deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#FeaturedBarker".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FeaturedBarker deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FeaturedBarker featuredBarker = new FeaturedBarker();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("backgroundTarget".equals(currentName)) {
                featuredBarker.setBackgroundTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("squareImage".equals(currentName)) {
                featuredBarker.setSquareImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                featuredBarker.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("altDescription".equals(currentName)) {
                featuredBarker.setAltDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("backgroundImages".equals(currentName)) {
                featuredBarker.setBackgroundImages(ImageListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                featuredBarker.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("squareTarget".equals(currentName)) {
                featuredBarker.setSquareTarget(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                featuredBarker.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return featuredBarker;
    }
}
