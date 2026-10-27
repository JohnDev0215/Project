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
import com.google.android.gms.common.internal.ImagesContract;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class SociallySharePlaylistResponseDeserializer extends JsonDeserializer<SociallySharePlaylistResponse> {
    public static final SociallySharePlaylistResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SociallySharePlaylistResponseDeserializer sociallySharePlaylistResponseDeserializer = new SociallySharePlaylistResponseDeserializer();
        INSTANCE = sociallySharePlaylistResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.SociallySharePlaylistResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SociallySharePlaylistResponse.class, sociallySharePlaylistResponseDeserializer);
    }

    private SociallySharePlaylistResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SociallySharePlaylistResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#SociallySharePlaylistResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SociallySharePlaylistResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SociallySharePlaylistResponse sociallySharePlaylistResponse = new SociallySharePlaylistResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("unshareableTracks".equals(currentName)) {
                sociallySharePlaylistResponse.setUnshareableTracks(PlaylistTrackEntriesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                sociallySharePlaylistResponse.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("collaborativeMode".equals(currentName)) {
                sociallySharePlaylistResponse.setCollaborativeMode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ImagesContract.URL.equals(currentName)) {
                sociallySharePlaylistResponse.setUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shareableTracks".equals(currentName)) {
                sociallySharePlaylistResponse.setShareableTracks(PlaylistTrackEntriesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistVersion".equals(currentName)) {
                sociallySharePlaylistResponse.setPlaylistVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("visibility".equals(currentName)) {
                sociallySharePlaylistResponse.setVisibility(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("socialPlaylistId".equals(currentName)) {
                sociallySharePlaylistResponse.setSocialPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("profileId".equals(currentName)) {
                sociallySharePlaylistResponse.setProfileId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("numberOfTracksInPlaylist".equals(currentName)) {
                sociallySharePlaylistResponse.setNumberOfTracksInPlaylist(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                sociallySharePlaylistResponse.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("numberOfShareableTracks".equals(currentName)) {
                sociallySharePlaylistResponse.setNumberOfShareableTracks(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return sociallySharePlaylistResponse;
    }
}
