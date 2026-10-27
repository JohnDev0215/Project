package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes2.dex */
public class FeaturedPlayDeserializer extends JsonDeserializer<FeaturedPlay> {
    public static final FeaturedPlayDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        FeaturedPlayDeserializer featuredPlayDeserializer = new FeaturedPlayDeserializer();
        INSTANCE = featuredPlayDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.FeaturedPlayDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(FeaturedPlay.class, featuredPlayDeserializer);
    }

    private FeaturedPlayDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public FeaturedPlay deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#FeaturedPlay".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public FeaturedPlay deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        FeaturedPlay featuredPlay = new FeaturedPlay();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("queueId".equals(currentName)) {
                featuredPlay.setQueueId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackOffsetIndex".equals(currentName)) {
                featuredPlay.setTrackOffsetIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("primaryTitle".equals(currentName)) {
                featuredPlay.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("altDescription".equals(currentName)) {
                featuredPlay.setAltDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("stationInstanceId".equals(currentName)) {
                featuredPlay.setStationInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playingStateTitle".equals(currentName)) {
                featuredPlay.setPlayingStateTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                featuredPlay.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackAsins".equals(currentName)) {
                featuredPlay.setTrackAsins(TrackAsinsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                featuredPlay.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("images".equals(currentName)) {
                featuredPlay.setImages(ImagesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("startNewQueue".equals(currentName)) {
                featuredPlay.setStartNewQueue(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("header".equals(currentName)) {
                featuredPlay.setHeader(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                featuredPlay.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                featuredPlay.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                featuredPlay.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("pageToken".equals(currentName)) {
                featuredPlay.setPageToken(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("callToActionTitle".equals(currentName)) {
                featuredPlay.setCallToActionTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackArtistsTitle".equals(currentName)) {
                featuredPlay.setTrackArtistsTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("entity".equals(currentName)) {
                featuredPlay.setEntity(EntityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pausedStateTitle".equals(currentName)) {
                featuredPlay.setPausedStateTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return featuredPlay;
    }
}
