package com.amazon.music.storeservice.model;

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

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedTrackItemDeserializer extends JsonDeserializer<RecommendedTrackItem> {
    public static final RecommendedTrackItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecommendedTrackItemDeserializer recommendedTrackItemDeserializer = new RecommendedTrackItemDeserializer();
        INSTANCE = recommendedTrackItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.RecommendedTrackItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecommendedTrackItem.class, recommendedTrackItemDeserializer);
    }

    private RecommendedTrackItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecommendedTrackItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#RecommendedTrackItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecommendedTrackItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecommendedTrackItem recommendedTrackItem = new RecommendedTrackItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recommendedTrackItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                recommendedTrackItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recommendedTrackItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                recommendedTrackItem.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                recommendedTrackItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                recommendedTrackItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumAverageOverallRating".equals(currentName)) {
                recommendedTrackItem.setAlbumAverageOverallRating(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                recommendedTrackItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recommendedTrackItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                recommendedTrackItem.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recommendedTrackItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                recommendedTrackItem.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recommendedTrackItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recommendedTrackItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                recommendedTrackItem.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumOriginalReleaseDate".equals(currentName)) {
                recommendedTrackItem.setAlbumOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                recommendedTrackItem.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("justification".equals(currentName)) {
                recommendedTrackItem.setJustification(JustificationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                recommendedTrackItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                recommendedTrackItem.setDurationSeconds(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recommendedTrackItem;
    }
}
