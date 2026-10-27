package com.amazon.musicensembleservice.brush;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.musicensembleservice.PlaymodeEligibilityDeserializer;
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

/* JADX INFO: loaded from: classes2.dex */
public class TrackDeserializer extends JsonDeserializer<Track> {
    public static final TrackDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        TrackDeserializer trackDeserializer = new TrackDeserializer();
        INSTANCE = trackDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.TrackDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Track.class, trackDeserializer);
    }

    private TrackDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Track deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#Track".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Track deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Track track = new Track();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("isInLibrary".equals(currentName)) {
                track.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("primaryTitle".equals(currentName)) {
                track.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                track.setDuration(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                track.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                track.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("shortTitle".equals(currentName)) {
                track.setShortTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                track.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("album".equals(currentName)) {
                track.setAlbum(AlbumDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("expirationTime".equals(currentName)) {
                track.setExpirationTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("localTimeZone".equals(currentName)) {
                track.setLocalTimeZone(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                track.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                track.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                track.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                track.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                track.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetType".equals(currentName)) {
                track.setAssetType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                track.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                track.setArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                track.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("localOriginalReleaseDate".equals(currentName)) {
                track.setLocalOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("genreName".equals(currentName)) {
                track.setGenreName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isTrending".equals(currentName)) {
                track.setIsTrending(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                track.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("globalAsin".equals(currentName)) {
                track.setGlobalAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("discNumber".equals(currentName)) {
                track.setDiscNumber(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("inLibraryId".equals(currentName)) {
                track.setInLibraryId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackNumber".equals(currentName)) {
                track.setTrackNumber(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("popularity".equals(currentName)) {
                track.setPopularity(SimpleDeserializers.deserializeDouble(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                track.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                track.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("concatenatedArtistName".equals(currentName)) {
                track.setConcatenatedArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetQualities".equals(currentName)) {
                track.setAssetQualities(AssetQualitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isOwned".equals(currentName)) {
                track.setIsOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("contentTiers".equals(currentName)) {
                track.setContentTiers(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("hasLyrics".equals(currentName)) {
                track.setHasLyrics(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_COMMENTARY.equals(currentName)) {
                track.setCommentary(CommentaryDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                track.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("singlePromotionDate".equals(currentName)) {
                track.setSinglePromotionDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("tags".equals(currentName)) {
                track.setTags(TagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return track;
    }
}
