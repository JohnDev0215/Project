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
public class MusicStoreRecommendedTrackItemDeserializer extends JsonDeserializer<MusicStoreRecommendedTrackItem> {
    public static final MusicStoreRecommendedTrackItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        MusicStoreRecommendedTrackItemDeserializer musicStoreRecommendedTrackItemDeserializer = new MusicStoreRecommendedTrackItemDeserializer();
        INSTANCE = musicStoreRecommendedTrackItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.MusicStoreRecommendedTrackItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(MusicStoreRecommendedTrackItem.class, musicStoreRecommendedTrackItemDeserializer);
    }

    private MusicStoreRecommendedTrackItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public MusicStoreRecommendedTrackItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#MusicStoreRecommendedTrackItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public MusicStoreRecommendedTrackItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        MusicStoreRecommendedTrackItem musicStoreRecommendedTrackItem = new MusicStoreRecommendedTrackItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                musicStoreRecommendedTrackItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                musicStoreRecommendedTrackItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumOriginalReleaseDate".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAlbumOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtImageUrl".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAlbumArtImageUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                musicStoreRecommendedTrackItem.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("price".equals(currentName)) {
                musicStoreRecommendedTrackItem.setPrice(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                musicStoreRecommendedTrackItem.setDurationSeconds(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("currency".equals(currentName)) {
                musicStoreRecommendedTrackItem.setCurrency(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                musicStoreRecommendedTrackItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                musicStoreRecommendedTrackItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("albumAverageOverallRating".equals(currentName)) {
                musicStoreRecommendedTrackItem.setAlbumAverageOverallRating(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("deliveryRestrictionList".equals(currentName)) {
                musicStoreRecommendedTrackItem.setDeliveryRestrictionList(MusicStoreDeliveryRestrictionsListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                musicStoreRecommendedTrackItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                musicStoreRecommendedTrackItem.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                musicStoreRecommendedTrackItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return musicStoreRecommendedTrackItem;
    }
}
