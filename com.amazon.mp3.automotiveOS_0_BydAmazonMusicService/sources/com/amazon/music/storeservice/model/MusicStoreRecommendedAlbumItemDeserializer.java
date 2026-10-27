package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
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
public class MusicStoreRecommendedAlbumItemDeserializer extends JsonDeserializer<MusicStoreRecommendedAlbumItem> {
    public static final MusicStoreRecommendedAlbumItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicStoreRecommendedAlbumItemDeserializer musicStoreRecommendedAlbumItemDeserializer = new MusicStoreRecommendedAlbumItemDeserializer();
        INSTANCE = musicStoreRecommendedAlbumItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.MusicStoreRecommendedAlbumItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MusicStoreRecommendedAlbumItem.class, musicStoreRecommendedAlbumItemDeserializer);
    }

    private MusicStoreRecommendedAlbumItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MusicStoreRecommendedAlbumItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#MusicStoreRecommendedAlbumItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MusicStoreRecommendedAlbumItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MusicStoreRecommendedAlbumItem musicStoreRecommendedAlbumItem = new MusicStoreRecommendedAlbumItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("currency".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("totalNumberOfTracks".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setTotalNumberOfTracks(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("averageOverallRating".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setAverageOverallRating(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setPrice(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                musicStoreRecommendedAlbumItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("totalReviewCount".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setTotalReviewCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deliveryRestrictionList".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setDeliveryRestrictionList(MusicStoreDeliveryRestrictionsListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                musicStoreRecommendedAlbumItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return musicStoreRecommendedAlbumItem;
    }
}
