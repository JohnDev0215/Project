package com.amazon.musicplaylist.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
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
public class GetPlaylistsByIdV2ResponseDeserializer extends JsonDeserializer<GetPlaylistsByIdV2Response> {
    public static final GetPlaylistsByIdV2ResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetPlaylistsByIdV2ResponseDeserializer getPlaylistsByIdV2ResponseDeserializer = new GetPlaylistsByIdV2ResponseDeserializer();
        INSTANCE = getPlaylistsByIdV2ResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplaylist.model.GetPlaylistsByIdV2ResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetPlaylistsByIdV2Response.class, getPlaylistsByIdV2ResponseDeserializer);
    }

    private GetPlaylistsByIdV2ResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetPlaylistsByIdV2Response deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplaylist.model#GetPlaylistsByIdV2Response".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetPlaylistsByIdV2Response deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetPlaylistsByIdV2Response getPlaylistsByIdV2Response = new GetPlaylistsByIdV2Response();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (MAPWebViewEventHelper.KEY_ERRORS.equals(currentName)) {
                getPlaylistsByIdV2Response.setErrors(PlaylistIdsToPlaylistErrorsMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlists".equals(currentName)) {
                getPlaylistsByIdV2Response.setPlaylists(PlaylistsV2Deserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("resyncLastPlaylistOnPreviousPage".equals(currentName)) {
                getPlaylistsByIdV2Response.setResyncLastPlaylistOnPreviousPage(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("nextPageToken".equals(currentName)) {
                getPlaylistsByIdV2Response.setNextPageToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isLastPlaylistSplit".equals(currentName)) {
                getPlaylistsByIdV2Response.setIsLastPlaylistSplit(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getPlaylistsByIdV2Response;
    }
}
