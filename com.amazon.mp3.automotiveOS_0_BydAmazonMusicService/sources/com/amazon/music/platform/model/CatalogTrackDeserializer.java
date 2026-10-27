package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.destination.parser.ParserUtil;
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
public class CatalogTrackDeserializer extends JsonDeserializer<CatalogTrack> {
    public static final CatalogTrackDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogTrackDeserializer catalogTrackDeserializer = new CatalogTrackDeserializer();
        INSTANCE = catalogTrackDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogTrackDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogTrack.class, catalogTrackDeserializer);
    }

    private CatalogTrackDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogTrack deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogTrack".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogTrack deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogTrack catalogTrack = new CatalogTrack();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("mood".equals(currentName)) {
                catalogTrack.setMood(MoodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("songWriter".equals(currentName)) {
                catalogTrack.setSongWriter(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                catalogTrack.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                catalogTrack.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogTrack.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artist".equals(currentName)) {
                catalogTrack.setArtist(TrackArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                catalogTrack.setAlternativeName(TrackNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                catalogTrack.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumPrimaryGenre".equals(currentName)) {
                catalogTrack.setAlbumPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isrc".equals(currentName)) {
                catalogTrack.setIsrc(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumRating".equals(currentName)) {
                catalogTrack.setAlbumRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                catalogTrack.setVersion(VersionsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("discNum".equals(currentName)) {
                catalogTrack.setDiscNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                catalogTrack.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogTrack.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("titleSetAsin".equals(currentName)) {
                catalogTrack.setTitleSetAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("lyricist".equals(currentName)) {
                catalogTrack.setLyricist(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("languageOfPerformances".equals(currentName)) {
                catalogTrack.setLanguageOfPerformances(LanguageOfPerformancesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumReleaseDate".equals(currentName)) {
                catalogTrack.setAlbumReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                catalogTrack.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumOriginalReleaseDate".equals(currentName)) {
                catalogTrack.setAlbumOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("publisher".equals(currentName)) {
                catalogTrack.setPublisher(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                catalogTrack.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("composer".equals(currentName)) {
                catalogTrack.setComposer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                catalogTrack.setGenre(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                catalogTrack.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                catalogTrack.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("era".equals(currentName)) {
                catalogTrack.setEra(ErasDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                catalogTrack.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                catalogTrack.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("dmid".equals(currentName)) {
                catalogTrack.setDmid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtistName".equals(currentName)) {
                catalogTrack.setAlbumArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogTrack.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                catalogTrack.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogTrack.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("relatedPlaylist".equals(currentName)) {
                catalogTrack.setRelatedPlaylist(RelatedPlaylistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumArtistAsin".equals(currentName)) {
                catalogTrack.setAlbumArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackNum".equals(currentName)) {
                catalogTrack.setTrackNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                catalogTrack.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                catalogTrack.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("languageOfPerformance".equals(currentName)) {
                catalogTrack.setLanguageOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogTrack.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                catalogTrack.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                catalogTrack.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("singlePromotionDate".equals(currentName)) {
                catalogTrack.setSinglePromotionDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("performer".equals(currentName)) {
                catalogTrack.setPerformer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogTrack.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogTrack.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                catalogTrack.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                catalogTrack.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogTrack;
    }
}
