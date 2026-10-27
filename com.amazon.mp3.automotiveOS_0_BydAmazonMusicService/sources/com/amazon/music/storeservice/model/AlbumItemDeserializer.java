package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.ContentEncodingsDeserializer;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.amazon.musicensembleservice.brush.ContentTypesDeserializer;
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
public class AlbumItemDeserializer extends JsonDeserializer<AlbumItem> {
    public static final AlbumItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlbumItemDeserializer albumItemDeserializer = new AlbumItemDeserializer();
        INSTANCE = albumItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.AlbumItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(AlbumItem.class, albumItemDeserializer);
    }

    private AlbumItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public AlbumItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#AlbumItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public AlbumItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        AlbumItem albumItem = new AlbumItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isPreorderEligible".equals(currentName)) {
                albumItem.setIsPreorderEligible(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("itemIndex".equals(currentName)) {
                albumItem.setItemIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                albumItem.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                albumItem.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                albumItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                albumItem.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                albumItem.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                albumItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                albumItem.setOriginalReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("albumPurchaseOptionsDataList".equals(currentName)) {
                albumItem.setAlbumPurchaseOptionsDataList(AlbumPurchaseOptionsDataListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                albumItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                albumItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                albumItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artist".equals(currentName)) {
                albumItem.setArtist(ArtistDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("productDetails".equals(currentName)) {
                albumItem.setProductDetails(ProductDetailsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryArtistName".equals(currentName)) {
                albumItem.setPrimaryArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                albumItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("globalAsin".equals(currentName)) {
                albumItem.setGlobalAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("streetDate".equals(currentName)) {
                albumItem.setStreetDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("merchantReleaseDate".equals(currentName)) {
                albumItem.setMerchantReleaseDate(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                albumItem.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isTwitchRadio".equals(currentName)) {
                albumItem.setIsTwitchRadio(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("reviews".equals(currentName)) {
                albumItem.setReviews(ReviewDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                albumItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                albumItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                albumItem.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                albumItem.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                albumItem.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("tracks".equals(currentName)) {
                albumItem.setTracks(TrackListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("purchase".equals(currentName)) {
                albumItem.setPurchase(PurchaseDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                albumItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                albumItem.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                albumItem.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                albumItem.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return albumItem;
    }
}
