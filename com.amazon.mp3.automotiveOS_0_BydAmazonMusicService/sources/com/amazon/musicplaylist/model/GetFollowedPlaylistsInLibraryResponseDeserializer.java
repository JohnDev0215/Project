package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
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

/* JADX INFO: loaded from: classes5.dex */
public class GetFollowedPlaylistsInLibraryResponseDeserializer extends JsonDeserializer<GetFollowedPlaylistsInLibraryResponse> {
    public static final GetFollowedPlaylistsInLibraryResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetFollowedPlaylistsInLibraryResponseDeserializer getFollowedPlaylistsInLibraryResponseDeserializer = new GetFollowedPlaylistsInLibraryResponseDeserializer();
        INSTANCE = getFollowedPlaylistsInLibraryResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetFollowedPlaylistsInLibraryResponse.class, getFollowedPlaylistsInLibraryResponseDeserializer);
    }

    private GetFollowedPlaylistsInLibraryResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetFollowedPlaylistsInLibraryResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#GetFollowedPlaylistsInLibraryResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetFollowedPlaylistsInLibraryResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetFollowedPlaylistsInLibraryResponse getFollowedPlaylistsInLibraryResponse = new GetFollowedPlaylistsInLibraryResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("nextPageToken".equals(currentName)) {
                getFollowedPlaylistsInLibraryResponse.setNextPageToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("numberOfFollowedPlaylists".equals(currentName)) {
                getFollowedPlaylistsInLibraryResponse.setNumberOfFollowedPlaylists(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("playlists".equals(currentName)) {
                getFollowedPlaylistsInLibraryResponse.setPlaylists(PlaylistMetadataListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getFollowedPlaylistsInLibraryResponse;
    }
}
