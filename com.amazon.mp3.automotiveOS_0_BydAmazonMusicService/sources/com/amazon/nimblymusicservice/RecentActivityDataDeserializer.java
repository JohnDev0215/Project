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
public class RecentActivityDataDeserializer extends JsonDeserializer<RecentActivityData> {
    public static final RecentActivityDataDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentActivityDataDeserializer recentActivityDataDeserializer = new RecentActivityDataDeserializer();
        INSTANCE = recentActivityDataDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentActivityDataDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentActivityData.class, recentActivityDataDeserializer);
    }

    private RecentActivityDataDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentActivityData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#RecentAlbumActivity".equals(text)) {
                return RecentAlbumActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentUserPlaylistActivity".equals(text)) {
                return RecentUserPlaylistActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentNonMetadataEntityActivity".equals(text)) {
                return RecentNonMetadataEntityActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentCatalogPlaylistActivity".equals(text)) {
                return RecentCatalogPlaylistActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentStationActivity".equals(text)) {
                return RecentStationActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentArtistActivity".equals(text)) {
                return RecentArtistActivityDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.nimblymusicservice#RecentActivityData".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentActivityData deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentActivityData recentActivityData = new RecentActivityData();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("imageFull".equals(currentName)) {
                recentActivityData.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                recentActivityData.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentActivityData.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                recentActivityData.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("uniqueIdentifier".equals(currentName)) {
                recentActivityData.setUniqueIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recentActivityData.setIsPrime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                recentActivityData.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recentActivityData.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recentActivityData.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackIdType".equals(currentName)) {
                recentActivityData.setTrackIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recentActivityData.setIsFree(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                recentActivityData.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMerlockExcluded".equals(currentName)) {
                recentActivityData.setIsMerlockExcluded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recentActivityData.setIsSonicRush(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.ENTITY_TYPE_QUERY_PARAM.equals(currentName)) {
                recentActivityData.setEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRA360Available".equals(currentName)) {
                recentActivityData.setIsRA360Available(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isAtmosAvailable".equals(currentName)) {
                recentActivityData.setIsAtmosAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("instanceId".equals(currentName)) {
                recentActivityData.setInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                recentActivityData.setTrackId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deviceTypeName".equals(currentName)) {
                recentActivityData.setDeviceTypeName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recentActivityData.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHiResAvailable".equals(currentName)) {
                recentActivityData.setIsHiResAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                recentActivityData.setIsSonicRushGolden(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isThreeDAvailable".equals(currentName)) {
                recentActivityData.setIsThreeDAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayName".equals(currentName)) {
                recentActivityData.setDisplayName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHdAvailable".equals(currentName)) {
                recentActivityData.setIsHdAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentActivityData;
    }
}
