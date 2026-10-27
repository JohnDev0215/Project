package com.amazon.layout.music.model;

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
public class HintDeserializer extends JsonDeserializer<Hint> {
    public static final HintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        HintDeserializer hintDeserializer = new HintDeserializer();
        INSTANCE = hintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.HintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Hint.class, hintDeserializer);
    }

    private HintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Hint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#RecentActivityHint".equals(text)) {
                return RecentActivityHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#SharedPlaylistHint".equals(text)) {
                return SharedPlaylistHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#UserPlaylistHint".equals(text)) {
                return UserPlaylistHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#TrackHint".equals(text)) {
                return TrackHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#AlbumHint".equals(text)) {
                return AlbumHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#TopicHint".equals(text)) {
                return TopicHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#ArtistHint".equals(text)) {
                return ArtistHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#TimerHint".equals(text)) {
                return TimerHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#PlaylistHint".equals(text)) {
                return PlaylistHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#StationHint".equals(text)) {
                return StationHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#ProgramHint".equals(text)) {
                return ProgramHintDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.layout.music.model#Hint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Hint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
