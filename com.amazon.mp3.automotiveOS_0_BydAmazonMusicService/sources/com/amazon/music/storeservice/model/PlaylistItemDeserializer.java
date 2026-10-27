package com.amazon.music.storeservice.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
import com.amazon.musicensembleservice.brush.ContentTypesDeserializer;
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
public class PlaylistItemDeserializer extends JsonDeserializer<PlaylistItem> {
    public static final PlaylistItemDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistItemDeserializer playlistItemDeserializer = new PlaylistItemDeserializer();
        INSTANCE = playlistItemDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.storeservice.model.PlaylistItemDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(PlaylistItem.class, playlistItemDeserializer);
    }

    private PlaylistItemDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public PlaylistItem deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.storeservice.model#PlaylistItem".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public PlaylistItem deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        PlaylistItem playlistItem = new PlaylistItem();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("requestedAsin".equals(currentName)) {
                playlistItem.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                playlistItem.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isTwitchRadio".equals(currentName)) {
                playlistItem.setIsTwitchRadio(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("languagesOfPerformance".equals(currentName)) {
                playlistItem.setLanguagesOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                playlistItem.setIsMusicSubscription(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("seriesAsin".equals(currentName)) {
                playlistItem.setSeriesAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                playlistItem.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                playlistItem.setIsPrime(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                playlistItem.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("reviews".equals(currentName)) {
                playlistItem.setReviews(ReviewDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                playlistItem.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("owned".equals(currentName)) {
                playlistItem.setOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("bannerImage".equals(currentName)) {
                playlistItem.setBannerImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tracks".equals(currentName)) {
                playlistItem.setTracks(TrackListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                playlistItem.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                playlistItem.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("relatedEntities".equals(currentName)) {
                playlistItem.setRelatedEntities(RelatedEntitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                playlistItem.setIsSonicRush(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("itemIndex".equals(currentName)) {
                playlistItem.setItemIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                playlistItem.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                playlistItem.setIsFree(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                playlistItem.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                playlistItem.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                playlistItem.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE.equals(currentName)) {
                playlistItem.setType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                playlistItem.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                playlistItem.setIsSonicRushGolden(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("authors".equals(currentName)) {
                playlistItem.setAuthors(StringListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("curationDescriptor".equals(currentName)) {
                playlistItem.setCurationDescriptor(CurationDescriptorListDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                playlistItem.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                playlistItem.setImage(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                playlistItem.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlistItem;
    }
}
