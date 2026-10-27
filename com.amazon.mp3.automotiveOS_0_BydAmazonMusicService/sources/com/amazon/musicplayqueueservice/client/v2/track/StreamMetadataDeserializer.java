package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicplayqueueservice.client.common.ParentalControlsDeserializer;
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
public class StreamMetadataDeserializer extends JsonDeserializer<StreamMetadata> {
    public static final StreamMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        StreamMetadataDeserializer streamMetadataDeserializer = new StreamMetadataDeserializer();
        INSTANCE = streamMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.StreamMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(StreamMetadata.class, streamMetadataDeserializer);
    }

    private StreamMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public StreamMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#StreamMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public StreamMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        StreamMetadata streamMetadata = new StreamMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("entityReference".equals(currentName)) {
                streamMetadata.setEntityReference(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                streamMetadata.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("metricsContext".equals(currentName)) {
                streamMetadata.setMetricsContext(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("clientMetricsInfo".equals(currentName)) {
                streamMetadata.setClientMetricsInfo(ClientMetricsInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNewQueue".equals(currentName)) {
                streamMetadata.setIsNewQueue(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("identifier".equals(currentName)) {
                streamMetadata.setIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("identifierType".equals(currentName)) {
                streamMetadata.setIdentifierType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                streamMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("capabilities".equals(currentName)) {
                streamMetadata.setCapabilities(CapabilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("durationInSeconds".equals(currentName)) {
                streamMetadata.setDurationInSeconds(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if ("serviceTier".equals(currentName)) {
                streamMetadata.setServiceTier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("programId".equals(currentName)) {
                streamMetadata.setProgramId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return streamMetadata;
    }
}
