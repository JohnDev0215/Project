package com.amazon.musicplayqueueservice.client.v2.track;

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
public class AdMetadataDeserializer extends JsonDeserializer<AdMetadata> {
    public static final AdMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AdMetadataDeserializer adMetadataDeserializer = new AdMetadataDeserializer();
        INSTANCE = adMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.AdMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AdMetadata.class, adMetadataDeserializer);
    }

    private AdMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AdMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#AdMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AdMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AdMetadata adMetadata = new AdMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("entityReference".equals(currentName)) {
                adMetadata.setEntityReference(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("productName".equals(currentName)) {
                adMetadata.setProductName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("advertiserName".equals(currentName)) {
                adMetadata.setAdvertiserName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("audioDetailOptions".equals(currentName)) {
                adMetadata.setAudioDetailOptions(AdAudioDetailsListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metricsContext".equals(currentName)) {
                adMetadata.setMetricsContext(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("clientMetricsInfo".equals(currentName)) {
                adMetadata.setClientMetricsInfo(ClientMetricsInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("visualDetailOptions".equals(currentName)) {
                adMetadata.setVisualDetailOptions(AdVisualDetailsListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNewQueue".equals(currentName)) {
                adMetadata.setIsNewQueue(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("capabilities".equals(currentName)) {
                adMetadata.setCapabilities(CapabilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("durationInSeconds".equals(currentName)) {
                adMetadata.setDurationInSeconds(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return adMetadata;
    }
}
