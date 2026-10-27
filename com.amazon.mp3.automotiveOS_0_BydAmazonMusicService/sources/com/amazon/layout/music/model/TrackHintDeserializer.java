package com.amazon.layout.music.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
import com.amazon.musicensembleservice.ParentalControlsDeserializer;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes3.dex */
public class TrackHintDeserializer extends JsonDeserializer<TrackHint> {
    public static final TrackHintDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackHintDeserializer trackHintDeserializer = new TrackHintDeserializer();
        INSTANCE = trackHintDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.layout.music.model.TrackHintDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackHint.class, trackHintDeserializer);
    }

    private TrackHintDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackHint deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.layout.music.model#TrackHint".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackHint deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackHint trackHint = new TrackHint();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("playmodeEligibility".equals(currentName)) {
                trackHint.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                trackHint.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                trackHint.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("recentlyPlayedEntityType".equals(currentName)) {
                trackHint.setRecentlyPlayedEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                trackHint.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                trackHint.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                trackHint.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("genreName".equals(currentName)) {
                trackHint.setGenreName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                trackHint.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                trackHint.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumArtistName".equals(currentName)) {
                trackHint.setAlbumArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                trackHint.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isRecommended".equals(currentName)) {
                trackHint.setIsRecommended(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("trackNumber".equals(currentName)) {
                trackHint.setTrackNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("pid".equals(currentName)) {
                trackHint.setPid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                trackHint.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("assetType".equals(currentName)) {
                trackHint.setAssetType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                trackHint.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                trackHint.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                trackHint.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                trackHint.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                trackHint.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                trackHint.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("discNumber".equals(currentName)) {
                trackHint.setDiscNumber(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumArtistAsin".equals(currentName)) {
                trackHint.setAlbumArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("popularity".equals(currentName)) {
                trackHint.setPopularity(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackHint;
    }
}
