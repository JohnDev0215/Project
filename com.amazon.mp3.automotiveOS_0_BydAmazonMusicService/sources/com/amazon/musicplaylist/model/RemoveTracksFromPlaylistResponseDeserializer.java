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
public class RemoveTracksFromPlaylistResponseDeserializer extends JsonDeserializer<RemoveTracksFromPlaylistResponse> {
    public static final RemoveTracksFromPlaylistResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RemoveTracksFromPlaylistResponseDeserializer removeTracksFromPlaylistResponseDeserializer = new RemoveTracksFromPlaylistResponseDeserializer();
        INSTANCE = removeTracksFromPlaylistResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.RemoveTracksFromPlaylistResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RemoveTracksFromPlaylistResponse.class, removeTracksFromPlaylistResponseDeserializer);
    }

    private RemoveTracksFromPlaylistResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RemoveTracksFromPlaylistResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#RemoveTracksFromPlaylistResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RemoveTracksFromPlaylistResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RemoveTracksFromPlaylistResponse removeTracksFromPlaylistResponse = new RemoveTracksFromPlaylistResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("version".equals(currentName)) {
                removeTracksFromPlaylistResponse.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("fourSquareArt".equals(currentName)) {
                removeTracksFromPlaylistResponse.setFourSquareArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                removeTracksFromPlaylistResponse.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("socialSharingErrors".equals(currentName)) {
                removeTracksFromPlaylistResponse.setSocialSharingErrors(GenericStringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("bannerArt".equals(currentName)) {
                removeTracksFromPlaylistResponse.setBannerArt(GenericStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return removeTracksFromPlaylistResponse;
    }
}
