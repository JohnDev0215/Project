package com.amazon.musicensembleservice;

import com.amazon.music.storeservice.model.AlbumListDeserializer;
import com.amazon.music.storeservice.model.GeneralMetadataDeserializer;
import com.amazon.music.storeservice.model.PlaylistListDeserializer;
import com.amazon.music.storeservice.model.TrackListDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesResponseDeserializer extends JsonDeserializer<GetTopMusicEntitiesResponse> {
    public static final GetTopMusicEntitiesResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetTopMusicEntitiesResponseDeserializer getTopMusicEntitiesResponseDeserializer = new GetTopMusicEntitiesResponseDeserializer();
        INSTANCE = getTopMusicEntitiesResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.GetTopMusicEntitiesResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetTopMusicEntitiesResponse.class, getTopMusicEntitiesResponseDeserializer);
    }

    private GetTopMusicEntitiesResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetTopMusicEntitiesResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice#GetTopMusicEntitiesResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetTopMusicEntitiesResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetTopMusicEntitiesResponse getTopMusicEntitiesResponse = new GetTopMusicEntitiesResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("playlistList".equals(currentName)) {
                getTopMusicEntitiesResponse.setPlaylistList(PlaylistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("titles".equals(currentName)) {
                getTopMusicEntitiesResponse.setTitles(GetTopMusicEntitiesTitlesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumList".equals(currentName)) {
                getTopMusicEntitiesResponse.setAlbumList(AlbumListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metadata".equals(currentName)) {
                getTopMusicEntitiesResponse.setMetadata(GeneralMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackList".equals(currentName)) {
                getTopMusicEntitiesResponse.setTrackList(TrackListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("nextTokenMap".equals(currentName)) {
                getTopMusicEntitiesResponse.setNextTokenMap(NextTokenMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getTopMusicEntitiesResponse;
    }
}
