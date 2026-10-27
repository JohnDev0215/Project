package com.amazon.music.storeservice.model;

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

/* JADX INFO: loaded from: classes4.dex */
public class MusicStoreAlbumRecommendationsDeserializer extends JsonDeserializer<MusicStoreAlbumRecommendations> {
    public static final MusicStoreAlbumRecommendationsDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicStoreAlbumRecommendationsDeserializer musicStoreAlbumRecommendationsDeserializer = new MusicStoreAlbumRecommendationsDeserializer();
        INSTANCE = musicStoreAlbumRecommendationsDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.MusicStoreAlbumRecommendationsDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MusicStoreAlbumRecommendations.class, musicStoreAlbumRecommendationsDeserializer);
    }

    private MusicStoreAlbumRecommendationsDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MusicStoreAlbumRecommendations deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#MusicStoreAlbumRecommendations".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MusicStoreAlbumRecommendations deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MusicStoreAlbumRecommendations musicStoreAlbumRecommendations = new MusicStoreAlbumRecommendations();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("widgetId".equals(currentName)) {
                musicStoreAlbumRecommendations.setWidgetId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albums".equals(currentName)) {
                musicStoreAlbumRecommendations.setAlbums(MusicStoreRecommendedAlbumItemListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                musicStoreAlbumRecommendations.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recommendationType".equals(currentName)) {
                musicStoreAlbumRecommendations.setRecommendationType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("nextResultsToken".equals(currentName)) {
                musicStoreAlbumRecommendations.setNextResultsToken(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                musicStoreAlbumRecommendations.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return musicStoreAlbumRecommendations;
    }
}
