package com.amazon.musicplaylist.model;

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

/* JADX INFO: loaded from: classes5.dex */
public class PlaylistTrackEntryDeserializer extends JsonDeserializer<PlaylistTrackEntry> {
    public static final PlaylistTrackEntryDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistTrackEntryDeserializer playlistTrackEntryDeserializer = new PlaylistTrackEntryDeserializer();
        INSTANCE = playlistTrackEntryDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.PlaylistTrackEntryDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaylistTrackEntry.class, playlistTrackEntryDeserializer);
    }

    private PlaylistTrackEntryDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaylistTrackEntry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#PlaylistTrackEntry".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaylistTrackEntry deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaylistTrackEntry playlistTrackEntry = new PlaylistTrackEntry();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("entryId".equals(currentName)) {
                playlistTrackEntry.setEntryId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                playlistTrackEntry.setTrackId(TrackIdDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metadata".equals(currentName)) {
                playlistTrackEntry.setMetadata(PlaylistTrackEntryMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("newSinceLastMajorVersion".equals(currentName)) {
                playlistTrackEntry.setNewSinceLastMajorVersion(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlistTrackEntry;
    }
}
