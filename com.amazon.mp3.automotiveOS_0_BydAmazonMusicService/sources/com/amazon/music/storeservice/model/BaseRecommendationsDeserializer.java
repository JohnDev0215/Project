package com.amazon.music.storeservice.model;

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
public class BaseRecommendationsDeserializer extends JsonDeserializer<BaseRecommendations> {
    public static final BaseRecommendationsDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        BaseRecommendationsDeserializer baseRecommendationsDeserializer = new BaseRecommendationsDeserializer();
        INSTANCE = baseRecommendationsDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.BaseRecommendationsDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(BaseRecommendations.class, baseRecommendationsDeserializer);
    }

    private BaseRecommendationsDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public BaseRecommendations deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#PlaylistRecommendations".equals(text)) {
                return PlaylistRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#TrackRecommendations".equals(text)) {
                return TrackRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#MusicStoreAlbumRecommendations".equals(text)) {
                return MusicStoreAlbumRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#AddToLibraryAlbumRecommendations".equals(text)) {
                return AddToLibraryAlbumRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#AlbumRecommendations".equals(text)) {
                return AlbumRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#StationRecommendations".equals(text)) {
                return StationRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#MusicStoreTrackRecommendations".equals(text)) {
                return MusicStoreTrackRecommendationsDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.storeservice.model#BaseRecommendations".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public BaseRecommendations deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
