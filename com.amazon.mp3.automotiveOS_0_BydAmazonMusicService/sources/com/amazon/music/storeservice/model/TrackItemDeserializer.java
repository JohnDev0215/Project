package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
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
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class TrackItemDeserializer extends JsonDeserializer<TrackItem> {
    public static final TrackItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackItemDeserializer trackItemDeserializer = new TrackItemDeserializer();
        INSTANCE = trackItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.TrackItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(TrackItem.class, trackItemDeserializer);
    }

    private TrackItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public TrackItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#TrackItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public TrackItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TrackItem trackItem = new TrackItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("title".equals(currentName)) {
                trackItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isrc".equals(currentName)) {
                trackItem.setIsrc(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                trackItem.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("reviews".equals(currentName)) {
                trackItem.setReviews(ReviewDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                trackItem.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artist".equals(currentName)) {
                trackItem.setArtist(ArtistDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("popularity".equals(currentName)) {
                trackItem.setPopularity(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                trackItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("transformedPopularity".equals(currentName)) {
                trackItem.setTransformedPopularity(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                trackItem.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("globalAsin".equals(currentName)) {
                trackItem.setGlobalAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtistName".equals(currentName)) {
                trackItem.setAlbumArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (voMimeTypes.VOBASE_TYPE_VIDEO.equals(currentName)) {
                trackItem.setVideo(VideoItemDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("album".equals(currentName)) {
                trackItem.setAlbum(AlbumDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryArtistName".equals(currentName)) {
                trackItem.setPrimaryArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                trackItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                trackItem.setOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("songWriters".equals(currentName)) {
                trackItem.setSongWriters(StringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                trackItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("assetType".equals(currentName)) {
                trackItem.setAssetType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("genreName".equals(currentName)) {
                trackItem.setGenreName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                trackItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                trackItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("pid".equals(currentName)) {
                trackItem.setPid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("owned".equals(currentName)) {
                trackItem.setOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("trackPurchaseOptionsData".equals(currentName)) {
                trackItem.setTrackPurchaseOptionsData(TrackPurchaseOptionsDataDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                trackItem.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                trackItem.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                trackItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("trackNum".equals(currentName)) {
                trackItem.setTrackNum(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPreorderEligible".equals(currentName)) {
                trackItem.setIsPreorderEligible(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                trackItem.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("streetDate".equals(currentName)) {
                trackItem.setStreetDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("albumArtistAsin".equals(currentName)) {
                trackItem.setAlbumArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                trackItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("purchase".equals(currentName)) {
                trackItem.setPurchase(PurchaseDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                trackItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("discNum".equals(currentName)) {
                trackItem.setDiscNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                trackItem.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetQualities".equals(currentName)) {
                trackItem.setAssetQualities(AssetQualityListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isTwitchRadio".equals(currentName)) {
                trackItem.setIsTwitchRadio(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                trackItem.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("itemIndex".equals(currentName)) {
                trackItem.setItemIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                trackItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hasLyrics".equals(currentName)) {
                trackItem.setHasLyrics(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return trackItem;
    }
}
