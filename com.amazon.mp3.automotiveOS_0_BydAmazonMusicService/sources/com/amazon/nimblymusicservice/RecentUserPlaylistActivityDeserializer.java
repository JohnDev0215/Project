package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes5.dex */
public class RecentUserPlaylistActivityDeserializer extends JsonDeserializer<RecentUserPlaylistActivity> {
    public static final RecentUserPlaylistActivityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentUserPlaylistActivityDeserializer recentUserPlaylistActivityDeserializer = new RecentUserPlaylistActivityDeserializer();
        INSTANCE = recentUserPlaylistActivityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentUserPlaylistActivityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentUserPlaylistActivity.class, recentUserPlaylistActivityDeserializer);
    }

    private RecentUserPlaylistActivityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentUserPlaylistActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#RecentUserPlaylistActivity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentUserPlaylistActivity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentUserPlaylistActivity recentUserPlaylistActivity = new RecentUserPlaylistActivity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("imageFull".equals(currentName)) {
                recentUserPlaylistActivity.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                recentUserPlaylistActivity.setTrackCount(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                recentUserPlaylistActivity.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentUserPlaylistActivity.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                recentUserPlaylistActivity.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("uniqueIdentifier".equals(currentName)) {
                recentUserPlaylistActivity.setUniqueIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curatedBy".equals(currentName)) {
                recentUserPlaylistActivity.setCuratedBy(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recentUserPlaylistActivity.setIsPrime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                recentUserPlaylistActivity.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recentUserPlaylistActivity.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFollowing".equals(currentName)) {
                recentUserPlaylistActivity.setIsFollowing(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                recentUserPlaylistActivity.setVersion(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recentUserPlaylistActivity.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackIdType".equals(currentName)) {
                recentUserPlaylistActivity.setTrackIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("sharingUrl".equals(currentName)) {
                recentUserPlaylistActivity.setSharingUrl(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recentUserPlaylistActivity.setIsFree(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                recentUserPlaylistActivity.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMerlockExcluded".equals(currentName)) {
                recentUserPlaylistActivity.setIsMerlockExcluded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recentUserPlaylistActivity.setIsSonicRush(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.ENTITY_TYPE_QUERY_PARAM.equals(currentName)) {
                recentUserPlaylistActivity.setEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRA360Available".equals(currentName)) {
                recentUserPlaylistActivity.setIsRA360Available(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isAtmosAvailable".equals(currentName)) {
                recentUserPlaylistActivity.setIsAtmosAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("instanceId".equals(currentName)) {
                recentUserPlaylistActivity.setInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("shareBlurb".equals(currentName)) {
                recentUserPlaylistActivity.setShareBlurb(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                recentUserPlaylistActivity.setTrackId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deviceTypeName".equals(currentName)) {
                recentUserPlaylistActivity.setDeviceTypeName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recentUserPlaylistActivity.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHiResAvailable".equals(currentName)) {
                recentUserPlaylistActivity.setIsHiResAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                recentUserPlaylistActivity.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                recentUserPlaylistActivity.setIsSonicRushGolden(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isThreeDAvailable".equals(currentName)) {
                recentUserPlaylistActivity.setIsThreeDAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isCurator".equals(currentName)) {
                recentUserPlaylistActivity.setIsCurator(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                recentUserPlaylistActivity.setDurationSeconds(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayName".equals(currentName)) {
                recentUserPlaylistActivity.setDisplayName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHdAvailable".equals(currentName)) {
                recentUserPlaylistActivity.setIsHdAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentUserPlaylistActivity;
    }
}
