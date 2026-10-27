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
public class ArtistHintDeserializer extends JsonDeserializer<ArtistHint> {
    public static final ArtistHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        ArtistHintDeserializer artistHintDeserializer = new ArtistHintDeserializer();
        INSTANCE = artistHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.ArtistHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(ArtistHint.class, artistHintDeserializer);
    }

    private ArtistHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public ArtistHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#ArtistHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public ArtistHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ArtistHint artistHint = new ArtistHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("timeStamp".equals(currentName)) {
                artistHint.setTimeStamp(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                artistHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                artistHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFollowed".equals(currentName)) {
                artistHint.setIsFollowed(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistImageUrl".equals(currentName)) {
                artistHint.setArtistImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                artistHint.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRecommended".equals(currentName)) {
                artistHint.setIsRecommended(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return artistHint;
    }
}
