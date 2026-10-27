package com.amazon.musicensembleservice;

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

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesTitlesDeserializer extends JsonDeserializer<GetTopMusicEntitiesTitles> {
    public static final GetTopMusicEntitiesTitlesDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTopMusicEntitiesTitlesDeserializer getTopMusicEntitiesTitlesDeserializer = new GetTopMusicEntitiesTitlesDeserializer();
        INSTANCE = getTopMusicEntitiesTitlesDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.GetTopMusicEntitiesTitlesDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetTopMusicEntitiesTitles.class, getTopMusicEntitiesTitlesDeserializer);
    }

    private GetTopMusicEntitiesTitlesDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetTopMusicEntitiesTitles deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice#GetTopMusicEntitiesTitles".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetTopMusicEntitiesTitles deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetTopMusicEntitiesTitles getTopMusicEntitiesTitles = new GetTopMusicEntitiesTitles();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("playlistList".equals(currentName)) {
                getTopMusicEntitiesTitles.setPlaylistList(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumList".equals(currentName)) {
                getTopMusicEntitiesTitles.setAlbumList(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackList".equals(currentName)) {
                getTopMusicEntitiesTitles.setTrackList(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getTopMusicEntitiesTitles;
    }
}
