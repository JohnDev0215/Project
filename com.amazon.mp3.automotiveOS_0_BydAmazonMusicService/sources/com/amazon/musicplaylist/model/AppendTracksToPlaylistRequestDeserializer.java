package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContextDeserializer;
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
public class AppendTracksToPlaylistRequestDeserializer extends JsonDeserializer<AppendTracksToPlaylistRequest> {
    public static final AppendTracksToPlaylistRequestDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AppendTracksToPlaylistRequestDeserializer appendTracksToPlaylistRequestDeserializer = new AppendTracksToPlaylistRequestDeserializer();
        INSTANCE = appendTracksToPlaylistRequestDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.AppendTracksToPlaylistRequestDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AppendTracksToPlaylistRequest.class, appendTracksToPlaylistRequestDeserializer);
    }

    private AppendTracksToPlaylistRequestDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AppendTracksToPlaylistRequest deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#AppendTracksToPlaylistRequest".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AppendTracksToPlaylistRequest deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AppendTracksToPlaylistRequest appendTracksToPlaylistRequest = new AppendTracksToPlaylistRequest();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("rejectDuplicateTracks".equals(currentName)) {
                appendTracksToPlaylistRequest.setRejectDuplicateTracks(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("customerId".equals(currentName)) {
                appendTracksToPlaylistRequest.setCustomerId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("featureSet".equals(currentName)) {
                appendTracksToPlaylistRequest.setFeatureSet(FeatureListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("musicRequestIdentityContext".equals(currentName)) {
                appendTracksToPlaylistRequest.setMusicRequestIdentityContext(MusicRequestIdentityContextDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_ID.equals(currentName)) {
                appendTracksToPlaylistRequest.setDeviceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("dependentServiceName".equals(currentName)) {
                appendTracksToPlaylistRequest.setDependentServiceName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("apiRevision".equals(currentName)) {
                appendTracksToPlaylistRequest.setApiRevision(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_TYPE.equals(currentName)) {
                appendTracksToPlaylistRequest.setDeviceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                appendTracksToPlaylistRequest.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playlistId".equals(currentName)) {
                appendTracksToPlaylistRequest.setPlaylistId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tracks".equals(currentName)) {
                appendTracksToPlaylistRequest.setTracks(TrackIdListAtLeastOneRequiredDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return appendTracksToPlaylistRequest;
    }
}
