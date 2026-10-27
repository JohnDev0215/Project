package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.android.gms.cast.MediaTrack;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedPlaylistItemDeserializer extends JsonDeserializer<RecommendedPlaylistItem> {
    public static final RecommendedPlaylistItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecommendedPlaylistItemDeserializer recommendedPlaylistItemDeserializer = new RecommendedPlaylistItemDeserializer();
        INSTANCE = recommendedPlaylistItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.RecommendedPlaylistItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecommendedPlaylistItem.class, recommendedPlaylistItemDeserializer);
    }

    private RecommendedPlaylistItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecommendedPlaylistItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#RecommendedPlaylistItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecommendedPlaylistItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecommendedPlaylistItem recommendedPlaylistItem = new RecommendedPlaylistItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("trackCount".equals(currentName)) {
                recommendedPlaylistItem.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("totalReviewCount".equals(currentName)) {
                recommendedPlaylistItem.setTotalReviewCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recommendedPlaylistItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("authorNames".equals(currentName)) {
                recommendedPlaylistItem.setAuthorNames(StringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                recommendedPlaylistItem.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                recommendedPlaylistItem.setIsSonicRushGolden(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recommendedPlaylistItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recommendedPlaylistItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                recommendedPlaylistItem.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recommendedPlaylistItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recommendedPlaylistItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("averageOverallRating".equals(currentName)) {
                recommendedPlaylistItem.setAverageOverallRating(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recommendedPlaylistItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                recommendedPlaylistItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                recommendedPlaylistItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                recommendedPlaylistItem.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                recommendedPlaylistItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("justification".equals(currentName)) {
                recommendedPlaylistItem.setJustification(JustificationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                recommendedPlaylistItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recommendedPlaylistItem;
    }
}
