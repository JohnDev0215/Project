package com.amazon.musicplayqueueservice.client.common;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicplayqueueservice.model.StringToStringMapDeserializer;
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
public class QueueMetadataDeserializer extends JsonDeserializer<QueueMetadata> {
    public static final QueueMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        QueueMetadataDeserializer queueMetadataDeserializer = new QueueMetadataDeserializer();
        INSTANCE = queueMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.QueueMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(QueueMetadata.class, queueMetadataDeserializer);
    }

    private QueueMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public QueueMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.common#QueueMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public QueueMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        QueueMetadata queueMetadata = new QueueMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("numberOfTracksRemainingBeforeReplenishing".equals(currentName)) {
                queueMetadata.setNumberOfTracksRemainingBeforeReplenishing(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("hideUpcomingTracks".equals(currentName)) {
                queueMetadata.setHideUpcomingTracks(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("imageUrlMap".equals(currentName)) {
                queueMetadata.setImageUrlMap(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("serviceTier".equals(currentName)) {
                queueMetadata.setServiceTier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canSkipBackward".equals(currentName)) {
                queueMetadata.setCanSkipBackward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("maximumTrackLookAhead".equals(currentName)) {
                queueMetadata.setMaximumTrackLookAhead(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                queueMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canSkipForward".equals(currentName)) {
                queueMetadata.setCanSkipForward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("maximumNumberOfTracksThatCanBeRequestedPerPage".equals(currentName)) {
                queueMetadata.setMaximumNumberOfTracksThatCanBeRequestedPerPage(SimpleDeserializers.deserializePrimitiveInt(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return queueMetadata;
    }
}
