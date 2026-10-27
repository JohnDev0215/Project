package com.amazon.nimblymusicservice;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class RecentPlayedTrackActivityDeserializer extends JsonDeserializer<RecentPlayedTrackActivity> {
    public static final RecentPlayedTrackActivityDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        RecentPlayedTrackActivityDeserializer recentPlayedTrackActivityDeserializer = new RecentPlayedTrackActivityDeserializer();
        INSTANCE = recentPlayedTrackActivityDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.nimblymusicservice.RecentPlayedTrackActivityDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(RecentPlayedTrackActivity.class, recentPlayedTrackActivityDeserializer);
    }

    private RecentPlayedTrackActivityDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public RecentPlayedTrackActivity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.nimblymusicservice#RecentPlayedTrackActivity".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public RecentPlayedTrackActivity deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        RecentPlayedTrackActivity recentPlayedTrackActivity = new RecentPlayedTrackActivity();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("displayName".equals(currentName)) {
                recentPlayedTrackActivity.setDisplayName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isUploaded".equals(currentName)) {
                recentPlayedTrackActivity.setIsUploaded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                recentPlayedTrackActivity.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                recentPlayedTrackActivity.setIsInLibrary(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isAtmosAvailable".equals(currentName)) {
                recentPlayedTrackActivity.setIsAtmosAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                recentPlayedTrackActivity.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isRA360Available".equals(currentName)) {
                recentPlayedTrackActivity.setIsRA360Available(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRushOnDemandPlayable".equals(currentName)) {
                recentPlayedTrackActivity.setIsSonicRushOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hasLyrics".equals(currentName)) {
                recentPlayedTrackActivity.setHasLyrics(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (TetheredMessageKey.timestamp.equals(currentName)) {
                recentPlayedTrackActivity.setTimestamp(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("uniqueIdentifier".equals(currentName)) {
                recentPlayedTrackActivity.setUniqueIdentifier(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_INFO.equals(currentName)) {
                recentPlayedTrackActivity.setContentInfo(ContentInfoDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isFree".equals(currentName)) {
                recentPlayedTrackActivity.setIsFree(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("imageFull".equals(currentName)) {
                recentPlayedTrackActivity.setImageFull(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isSonicRush".equals(currentName)) {
                recentPlayedTrackActivity.setIsSonicRush(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHiResAvailable".equals(currentName)) {
                recentPlayedTrackActivity.setIsHiResAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                recentPlayedTrackActivity.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInstantImport".equals(currentName)) {
                recentPlayedTrackActivity.setIsInstantImport(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMerlockExcluded".equals(currentName)) {
                recentPlayedTrackActivity.setIsMerlockExcluded(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isThreeDAvailable".equals(currentName)) {
                recentPlayedTrackActivity.setIsThreeDAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isHdAvailable".equals(currentName)) {
                recentPlayedTrackActivity.setIsHdAvailable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_ID.equals(currentName)) {
                recentPlayedTrackActivity.setDeviceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isFreeOnDemandPlayable".equals(currentName)) {
                recentPlayedTrackActivity.setIsFreeOnDemandPlayable(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                recentPlayedTrackActivity.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("libraryStatus".equals(currentName)) {
                recentPlayedTrackActivity.setLibraryStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("durationSeconds".equals(currentName)) {
                recentPlayedTrackActivity.setDurationSeconds(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                recentPlayedTrackActivity.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                recentPlayedTrackActivity.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPrime".equals(currentName)) {
                recentPlayedTrackActivity.setIsPrime(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                recentPlayedTrackActivity.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                recentPlayedTrackActivity.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isPurchased".equals(currentName)) {
                recentPlayedTrackActivity.setIsPurchased(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                recentPlayedTrackActivity.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_DEVICE_TYPE.equals(currentName)) {
                recentPlayedTrackActivity.setDeviceType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                recentPlayedTrackActivity.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return recentPlayedTrackActivity;
    }
}
