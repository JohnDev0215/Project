package com.amazon.music.storeservice.model;

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

/* JADX INFO: loaded from: classes4.dex */
public class LookupResponseDeserializer extends JsonDeserializer<LookupResponse> {
    public static final LookupResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LookupResponseDeserializer lookupResponseDeserializer = new LookupResponseDeserializer();
        INSTANCE = lookupResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.LookupResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LookupResponse.class, lookupResponseDeserializer);
    }

    private LookupResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LookupResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#LookupResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LookupResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LookupResponse lookupResponse = new LookupResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("albumList".equals(currentName)) {
                lookupResponse.setAlbumList(AlbumListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackList".equals(currentName)) {
                lookupResponse.setTrackList(TrackListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistList".equals(currentName)) {
                lookupResponse.setPlaylistList(PlaylistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metadata".equals(currentName)) {
                lookupResponse.setMetadata(GeneralMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistList".equals(currentName)) {
                lookupResponse.setArtistList(ArtistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return lookupResponse;
    }
}
