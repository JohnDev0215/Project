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
public class RecentAlbumActivityDeserializer extends JsonDeserializer<RecentAlbumActivity> {
    public static final RecentAlbumActivityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentAlbumActivityDeserializer recentAlbumActivityDeserializer = new RecentAlbumActivityDeserializer();
        INSTANCE = recentAlbumActivityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentAlbumActivityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentAlbumActivity.class, recentAlbumActivityDeserializer);
    }

    private RecentAlbumActivityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentAlbumActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentAlbumActivity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentAlbumActivity recentAlbumActivity = new RecentAlbumActivity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("imageFull".equals(currentName)) {
                recentAlbumActivity.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                recentAlbumActivity.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                recentAlbumActivity.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentAlbumActivity.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID_TYPE.equals(currentName)) {
                recentAlbumActivity.setEntityIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("uniqueIdentifier".equals(currentName)) {
                recentAlbumActivity.setUniqueIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recentAlbumActivity.setIsPrime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                recentAlbumActivity.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                recentAlbumActivity.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recentAlbumActivity.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recentAlbumActivity.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackIdType".equals(currentName)) {
                recentAlbumActivity.setTrackIdType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recentAlbumActivity.setIsFree(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.ENTITY_ID.equals(currentName)) {
                recentAlbumActivity.setEntityId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMerlockExcluded".equals(currentName)) {
                recentAlbumActivity.setIsMerlockExcluded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recentAlbumActivity.setIsSonicRush(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (PageUriUtils.ENTITY_TYPE_QUERY_PARAM.equals(currentName)) {
                recentAlbumActivity.setEntityType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRA360Available".equals(currentName)) {
                recentAlbumActivity.setIsRA360Available(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isAtmosAvailable".equals(currentName)) {
                recentAlbumActivity.setIsAtmosAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("instanceId".equals(currentName)) {
                recentAlbumActivity.setInstanceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackId".equals(currentName)) {
                recentAlbumActivity.setTrackId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("deviceTypeName".equals(currentName)) {
                recentAlbumActivity.setDeviceTypeName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recentAlbumActivity.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHiResAvailable".equals(currentName)) {
                recentAlbumActivity.setIsHiResAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushGolden".equals(currentName)) {
                recentAlbumActivity.setIsSonicRushGolden(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isThreeDAvailable".equals(currentName)) {
                recentAlbumActivity.setIsThreeDAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                recentAlbumActivity.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("displayName".equals(currentName)) {
                recentAlbumActivity.setDisplayName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHdAvailable".equals(currentName)) {
                recentAlbumActivity.setIsHdAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentAlbumActivity;
    }
}
