package com.amazon.music.platform.model;

import androidx.core.app.NotificationCompat;
import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
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
public class LibraryTrackDeserializer extends JsonDeserializer<LibraryTrack> {
    public static final LibraryTrackDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        LibraryTrackDeserializer libraryTrackDeserializer = new LibraryTrackDeserializer();
        INSTANCE = libraryTrackDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.LibraryTrackDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(LibraryTrack.class, libraryTrackDeserializer);
    }

    private LibraryTrackDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public LibraryTrack deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#LibraryTrack".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public LibraryTrack deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        LibraryTrack libraryTrack = new LibraryTrack();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("songWriter".equals(currentName)) {
                libraryTrack.setSongWriter(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                libraryTrack.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                libraryTrack.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                libraryTrack.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumPrimaryGenre".equals(currentName)) {
                libraryTrack.setAlbumPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isrc".equals(currentName)) {
                libraryTrack.setIsrc(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isKatana".equals(currentName)) {
                libraryTrack.setIsKatana(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("objectId".equals(currentName)) {
                libraryTrack.setObjectId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumRating".equals(currentName)) {
                libraryTrack.setAlbumRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("discNum".equals(currentName)) {
                libraryTrack.setDiscNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                libraryTrack.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                libraryTrack.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("fileSize".equals(currentName)) {
                libraryTrack.setFileSize(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("lyricist".equals(currentName)) {
                libraryTrack.setLyricist(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumReleaseDate".equals(currentName)) {
                libraryTrack.setAlbumReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                libraryTrack.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetListKey".equals(currentName)) {
                libraryTrack.setAssetListKey(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("uploaded".equals(currentName)) {
                libraryTrack.setUploaded(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("publisher".equals(currentName)) {
                libraryTrack.setPublisher(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rogueBackfillDate".equals(currentName)) {
                libraryTrack.setRogueBackfillDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                libraryTrack.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("composer".equals(currentName)) {
                libraryTrack.setComposer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                libraryTrack.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                libraryTrack.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("assetType".equals(currentName)) {
                libraryTrack.setAssetType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (NotificationCompat.CATEGORY_STATUS.equals(currentName)) {
                libraryTrack.setStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("matchType".equals(currentName)) {
                libraryTrack.setMatchType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("lastUpdatedDate".equals(currentName)) {
                libraryTrack.setLastUpdatedDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                libraryTrack.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contributors".equals(currentName)) {
                libraryTrack.setContributors(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("dmid".equals(currentName)) {
                libraryTrack.setDmid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("localFilePath".equals(currentName)) {
                libraryTrack.setLocalFilePath(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("instantImport".equals(currentName)) {
                libraryTrack.setInstantImport(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("physicalOrderId".equals(currentName)) {
                libraryTrack.setPhysicalOrderId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtistName".equals(currentName)) {
                libraryTrack.setAlbumArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                libraryTrack.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("errorCode".equals(currentName)) {
                libraryTrack.setErrorCode(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                libraryTrack.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                libraryTrack.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("creationDate".equals(currentName)) {
                libraryTrack.setCreationDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("albumArtistAsin".equals(currentName)) {
                libraryTrack.setAlbumArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackNum".equals(currentName)) {
                libraryTrack.setTrackNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("purchased".equals(currentName)) {
                libraryTrack.setPurchased(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("fileExtension".equals(currentName)) {
                libraryTrack.setFileExtension(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                libraryTrack.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("bitrate".equals(currentName)) {
                libraryTrack.setBitrate(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                libraryTrack.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                libraryTrack.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rating".equals(currentName)) {
                libraryTrack.setRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("purchaseDate".equals(currentName)) {
                libraryTrack.setPurchaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                libraryTrack.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("fileName".equals(currentName)) {
                libraryTrack.setFileName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("performer".equals(currentName)) {
                libraryTrack.setPerformer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumContributors".equals(currentName)) {
                libraryTrack.setAlbumContributors(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                libraryTrack.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                libraryTrack.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                libraryTrack.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("orderId".equals(currentName)) {
                libraryTrack.setOrderId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("md5".equals(currentName)) {
                libraryTrack.setMd5(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                libraryTrack.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return libraryTrack;
    }
}
