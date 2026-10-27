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
public class TrackMetadataDeserializer extends JsonDeserializer<TrackMetadata> {
    public static final TrackMetadataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackMetadataDeserializer trackMetadataDeserializer = new TrackMetadataDeserializer();
        INSTANCE = trackMetadataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicplayqueueservice.client.common.TrackMetadataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackMetadata.class, trackMetadataDeserializer);
    }

    private TrackMetadataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackMetadata deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicplayqueueservice.client.common#TrackMetadata".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackMetadata deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackMetadata trackMetadata = new TrackMetadata();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("artUrlMap".equals(currentName)) {
                trackMetadata.setArtUrlMap(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("album".equals(currentName)) {
                trackMetadata.setAlbum(TrackAlbumDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("identifierType".equals(currentName)) {
                trackMetadata.setIdentifierType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                trackMetadata.setRating(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("canScrubBackward".equals(currentName)) {
                trackMetadata.setCanScrubBackward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("programMetadata".equals(currentName)) {
                trackMetadata.setProgramMetadata(ProgramMetadataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("assetQualities".equals(currentName)) {
                trackMetadata.setAssetQualities(AssetQualitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("metricsContext".equals(currentName)) {
                trackMetadata.setMetricsContext(StringToStringMapDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                trackMetadata.setTrackId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationInSeconds".equals(currentName)) {
                trackMetadata.setDurationInSeconds(SimpleDeserializers.deserializePrimitiveDouble(jsonParser, deserializationContext));
            } else if ("hasLyrics".equals(currentName)) {
                trackMetadata.setHasLyrics(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("canSkipForward".equals(currentName)) {
                trackMetadata.setCanSkipForward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("identifier".equals(currentName)) {
                trackMetadata.setIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("streamingURL".equals(currentName)) {
                trackMetadata.setStreamingURL(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("cuePoints".equals(currentName)) {
                trackMetadata.setCuePoints(CuePointsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isNewQueue".equals(currentName)) {
                trackMetadata.setIsNewQueue(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("name".equals(currentName)) {
                trackMetadata.setName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                trackMetadata.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canSkipBackward".equals(currentName)) {
                trackMetadata.setCanSkipBackward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("serviceTier".equals(currentName)) {
                trackMetadata.setServiceTier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                trackMetadata.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                trackMetadata.setPrice(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                trackMetadata.setArtists(TrackArtistListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("canRateTrack".equals(currentName)) {
                trackMetadata.setCanRateTrack(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("isSample".equals(currentName)) {
                trackMetadata.setIsSample(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("canScrubForward".equals(currentName)) {
                trackMetadata.setCanScrubForward(SimpleDeserializers.deserializePrimitiveBoolean(jsonParser, deserializationContext));
            } else if ("assetType".equals(currentName)) {
                trackMetadata.setAssetType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackMetadata;
    }
}
