package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.skyfire.models.TetheredMessageKey;
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
public class TrackEventDeserializer extends JsonDeserializer<TrackEvent> {
    public static final TrackEventDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackEventDeserializer trackEventDeserializer = new TrackEventDeserializer();
        INSTANCE = trackEventDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.TrackEventDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackEvent.class, trackEventDeserializer);
    }

    private TrackEventDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackEvent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#TrackEvent".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackEvent deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackEvent trackEvent = new TrackEvent();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                trackEvent.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                trackEvent.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                trackEvent.setDurationSeconds(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                trackEvent.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackEvent;
    }
}
