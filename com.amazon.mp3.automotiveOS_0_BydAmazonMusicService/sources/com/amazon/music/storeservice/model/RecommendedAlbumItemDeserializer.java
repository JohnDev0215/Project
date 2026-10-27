package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
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
public class RecommendedAlbumItemDeserializer extends JsonDeserializer<RecommendedAlbumItem> {
    public static final RecommendedAlbumItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecommendedAlbumItemDeserializer recommendedAlbumItemDeserializer = new RecommendedAlbumItemDeserializer();
        INSTANCE = recommendedAlbumItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.RecommendedAlbumItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecommendedAlbumItem.class, recommendedAlbumItemDeserializer);
    }

    private RecommendedAlbumItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecommendedAlbumItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#RecommendedAlbumItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecommendedAlbumItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecommendedAlbumItem recommendedAlbumItem = new RecommendedAlbumItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("artistAsin".equals(currentName)) {
                recommendedAlbumItem.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recommendedAlbumItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("averageOverallRating".equals(currentName)) {
                recommendedAlbumItem.setAverageOverallRating(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("totalNumberOfTracks".equals(currentName)) {
                recommendedAlbumItem.setTotalNumberOfTracks(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recommendedAlbumItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("totalReviewCount".equals(currentName)) {
                recommendedAlbumItem.setTotalReviewCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recommendedAlbumItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                recommendedAlbumItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                recommendedAlbumItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recommendedAlbumItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recommendedAlbumItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                recommendedAlbumItem.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                recommendedAlbumItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("justification".equals(currentName)) {
                recommendedAlbumItem.setJustification(JustificationDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recommendedAlbumItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                recommendedAlbumItem.setOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                recommendedAlbumItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                recommendedAlbumItem.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recommendedAlbumItem;
    }
}
