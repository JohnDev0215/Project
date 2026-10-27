package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.music.skyfire.models.TetheredMessageKey;
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

/* JADX INFO: loaded from: classes5.dex */
public class RecentCatalogPlaylistActivityDeserializer extends JsonDeserializer<RecentCatalogPlaylistActivity> {
    public static final RecentCatalogPlaylistActivityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentCatalogPlaylistActivityDeserializer recentCatalogPlaylistActivityDeserializer = new RecentCatalogPlaylistActivityDeserializer();
        INSTANCE = recentCatalogPlaylistActivityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentCatalogPlaylistActivityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentCatalogPlaylistActivity.class, recentCatalogPlaylistActivityDeserializer);
    }

    private RecentCatalogPlaylistActivityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentCatalogPlaylistActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#RecentCatalogPlaylistActivity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentCatalogPlaylistActivity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentCatalogPlaylistActivity recentCatalogPlaylistActivity = new RecentCatalogPlaylistActivity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("imageFull".equals(currentName)) {
                recentCatalogPlaylistActivity.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentPlaylist".equals(currentName)) {
                recentCatalogPlaylistActivity.setParentPlaylist(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                recentCatalogPlaylistActivity.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFollowing".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsFollowing(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                recentCatalogPlaylistActivity.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentCatalogPlaylistActivity.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                recentCatalogPlaylistActivity.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("uniqueIdentifier".equals(currentName)) {
                recentCatalogPlaylistActivity.setUniqueIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsPrime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                recentCatalogPlaylistActivity.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackIdType".equals(currentName)) {
                recentCatalogPlaylistActivity.setTrackIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                recentCatalogPlaylistActivity.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsFree(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                recentCatalogPlaylistActivity.setGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                recentCatalogPlaylistActivity.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                recentCatalogPlaylistActivity.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMerlockExcluded".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsMerlockExcluded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsSonicRush(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.ENTITY_TYPE_QUERY_PARAM.equals(currentName)) {
                recentCatalogPlaylistActivity.setEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRA360Available".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsRA360Available(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isAtmosAvailable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsAtmosAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("instanceId".equals(currentName)) {
                recentCatalogPlaylistActivity.setInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                recentCatalogPlaylistActivity.setTrackId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("updatedDate".equals(currentName)) {
                recentCatalogPlaylistActivity.setUpdatedDate(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deviceTypeName".equals(currentName)) {
                recentCatalogPlaylistActivity.setDeviceTypeName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                recentCatalogPlaylistActivity.setDurationSeconds(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHiResAvailable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsHiResAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsSonicRushGolden(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isThreeDAvailable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsThreeDAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayName".equals(currentName)) {
                recentCatalogPlaylistActivity.setDisplayName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHdAvailable".equals(currentName)) {
                recentCatalogPlaylistActivity.setIsHdAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentCatalogPlaylistActivity;
    }
}
