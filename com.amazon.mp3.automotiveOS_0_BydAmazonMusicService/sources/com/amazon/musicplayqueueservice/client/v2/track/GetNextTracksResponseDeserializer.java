package com.amazon.musicplayqueueservice.client.v2.track;

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
public class GetNextTracksResponseDeserializer extends JsonDeserializer<GetNextTracksResponse> {
    public static final GetNextTracksResponseDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GetNextTracksResponseDeserializer getNextTracksResponseDeserializer = new GetNextTracksResponseDeserializer();
        INSTANCE = getNextTracksResponseDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.GetNextTracksResponseDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GetNextTracksResponse.class, getNextTracksResponseDeserializer);
    }

    private GetNextTracksResponseDeserializer() {
    }

    public static void register(ObjectMapper objectMapper) {
        objectMapper.registerModule(MODULE);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GetNextTracksResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#GetNextTracksResponse".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GetNextTracksResponse deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GetNextTracksResponse getNextTracksResponse = new GetNextTracksResponse();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("allowedRetryTimestampMs".equals(currentName)) {
                getNextTracksResponse.setAllowedRetryTimestampMs(SimpleDeserializers.deserializePrimitiveLong(jsonParser, deserializationContext));
            } else if ("queuedEntityMetadataList".equals(currentName)) {
                getNextTracksResponse.setQueuedEntityMetadataList(QueuedEntityMetadataListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("nextPageToken".equals(currentName)) {
                getNextTracksResponse.setNextPageToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("responseStatus".equals(currentName)) {
                getNextTracksResponse.setResponseStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return getNextTracksResponse;
    }
}
