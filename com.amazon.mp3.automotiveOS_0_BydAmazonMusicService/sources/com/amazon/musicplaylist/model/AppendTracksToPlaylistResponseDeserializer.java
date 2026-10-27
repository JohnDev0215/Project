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
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class AppendTracksToPlaylistResponseDeserializer extends JsonDeserializer<AppendTracksToPlaylistResponse> {
    public static final AppendTracksToPlaylistResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AppendTracksToPlaylistResponseDeserializer appendTracksToPlaylistResponseDeserializer = new AppendTracksToPlaylistResponseDeserializer();
        INSTANCE = appendTracksToPlaylistResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.AppendTracksToPlaylistResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AppendTracksToPlaylistResponse.class, appendTracksToPlaylistResponseDeserializer);
    }

    private AppendTracksToPlaylistResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AppendTracksToPlaylistResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#AppendTracksToPlaylistResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AppendTracksToPlaylistResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AppendTracksToPlaylistResponse appendTracksToPlaylistResponse = new AppendTracksToPlaylistResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("version".equals(currentName)) {
                appendTracksToPlaylistResponse.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                appendTracksToPlaylistResponse.setFourSquareArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                appendTracksToPlaylistResponse.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("socialSharingErrors".equals(currentName)) {
                appendTracksToPlaylistResponse.setSocialSharingErrors(GenericStringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                appendTracksToPlaylistResponse.setBannerArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return appendTracksToPlaylistResponse;
    }
}
