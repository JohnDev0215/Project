package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.musicplayqueueservice.client.common.ParentalControlsDeserializer;
import com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifierDeserializer;
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
public class GenericAudioMetadataDeserializer extends JsonDeserializer<GenericAudioMetadata> {
    public static final GenericAudioMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        GenericAudioMetadataDeserializer genericAudioMetadataDeserializer = new GenericAudioMetadataDeserializer();
        INSTANCE = genericAudioMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.GenericAudioMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(GenericAudioMetadata.class, genericAudioMetadataDeserializer);
    }

    private GenericAudioMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public GenericAudioMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#GenericAudioMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public GenericAudioMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        GenericAudioMetadata genericAudioMetadata = new GenericAudioMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("parentalControls".equals(currentName)) {
                genericAudioMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("clientMetricsInfo".equals(currentName)) {
                genericAudioMetadata.setClientMetricsInfo(ClientMetricsInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("imageMetadata".equals(currentName)) {
                genericAudioMetadata.setImageMetadata(ImageMetadataBaseDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("capabilities".equals(currentName)) {
                genericAudioMetadata.setCapabilities(CapabilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                genericAudioMetadata.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("entityReference".equals(currentName)) {
                genericAudioMetadata.setEntityReference(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("subTitle".equals(currentName)) {
                genericAudioMetadata.setSubTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("identifier".equals(currentName)) {
                genericAudioMetadata.setIdentifier(AbstractPlayableEntityIdentifierDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metricsContext".equals(currentName)) {
                genericAudioMetadata.setMetricsContext(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNewQueue".equals(currentName)) {
                genericAudioMetadata.setIsNewQueue(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("serviceTier".equals(currentName)) {
                genericAudioMetadata.setServiceTier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                genericAudioMetadata.setRating(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationInSeconds".equals(currentName)) {
                genericAudioMetadata.setDurationInSeconds(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if (PageUriUtils.SOURCE_QUERY_PARAM.equals(currentName)) {
                genericAudioMetadata.setSource(AudioSourceBaseDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return genericAudioMetadata;
    }
}
