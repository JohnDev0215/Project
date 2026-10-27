package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicplayqueueservice.client.common.ParentalControlsDeserializer;
import com.amazon.musicplayqueueservice.client.common.TrackAlbumDeserializer;
import com.amazon.musicplayqueueservice.client.common.TrackArtistListDeserializer;
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
public class SongMetadataDeserializer extends JsonDeserializer<SongMetadata> {
    public static final SongMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        SongMetadataDeserializer songMetadataDeserializer = new SongMetadataDeserializer();
        INSTANCE = songMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.v2.track.SongMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(SongMetadata.class, songMetadataDeserializer);
    }

    private SongMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public SongMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.v2.track#SongMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public SongMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        SongMetadata songMetadata = new SongMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("identifierType".equals(currentName)) {
                songMetadata.setIdentifierType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("serviceTier".equals(currentName)) {
                songMetadata.setServiceTier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("clientMetricsInfo".equals(currentName)) {
                songMetadata.setClientMetricsInfo(ClientMetricsInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("hasLyrics".equals(currentName)) {
                songMetadata.setHasLyrics(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                songMetadata.setArtists(TrackArtistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                songMetadata.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                songMetadata.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("capabilities".equals(currentName)) {
                songMetadata.setCapabilities(CapabilitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("identifier".equals(currentName)) {
                songMetadata.setIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                songMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("entityReference".equals(currentName)) {
                songMetadata.setEntityReference(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSample".equals(currentName)) {
                songMetadata.setIsSample(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("metricsContext".equals(currentName)) {
                songMetadata.setMetricsContext(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNewQueue".equals(currentName)) {
                songMetadata.setIsNewQueue(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("albumArt".equals(currentName)) {
                songMetadata.setAlbumArt(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("album".equals(currentName)) {
                songMetadata.setAlbum(TrackAlbumDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                songMetadata.setRating(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("licenseKeyToken".equals(currentName)) {
                songMetadata.setLicenseKeyToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationInSeconds".equals(currentName)) {
                songMetadata.setDurationInSeconds(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return songMetadata;
    }
}
