package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.inappmessaging.internal.model.Splash;
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
public class CatalogVideoDeserializer extends JsonDeserializer<CatalogVideo> {
    public static final CatalogVideoDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogVideoDeserializer catalogVideoDeserializer = new CatalogVideoDeserializer();
        INSTANCE = catalogVideoDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogVideoDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogVideo.class, catalogVideoDeserializer);
    }

    private CatalogVideoDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogVideo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogVideo".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogVideo deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogVideo catalogVideo = new CatalogVideo();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("publisher".equals(currentName)) {
                catalogVideo.setPublisher(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                catalogVideo.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("singlePromotionDate".equals(currentName)) {
                catalogVideo.setSinglePromotionDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                catalogVideo.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                catalogVideo.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogVideo.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("albumReleaseDate".equals(currentName)) {
                catalogVideo.setAlbumReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("trackNum".equals(currentName)) {
                catalogVideo.setTrackNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumRating".equals(currentName)) {
                catalogVideo.setAlbumRating(SimpleDeserializers.deserializeFloat(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                catalogVideo.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                catalogVideo.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogVideo.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("titleSetAsin".equals(currentName)) {
                catalogVideo.setTitleSetAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                catalogVideo.setAlternativeName(VideoNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("composer".equals(currentName)) {
                catalogVideo.setComposer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumOriginalReleaseDate".equals(currentName)) {
                catalogVideo.setAlbumOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                catalogVideo.setVersion(VersionsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                catalogVideo.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("albumName".equals(currentName)) {
                catalogVideo.setAlbumName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtistName".equals(currentName)) {
                catalogVideo.setAlbumArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                catalogVideo.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumPrimaryGenre".equals(currentName)) {
                catalogVideo.setAlbumPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                catalogVideo.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistAsin".equals(currentName)) {
                catalogVideo.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isrc".equals(currentName)) {
                catalogVideo.setIsrc(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                catalogVideo.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                catalogVideo.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogVideo.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogVideo.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("era".equals(currentName)) {
                catalogVideo.setEra(ErasDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("mood".equals(currentName)) {
                catalogVideo.setMood(MoodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogVideo.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("dmid".equals(currentName)) {
                catalogVideo.setDmid(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("songWriter".equals(currentName)) {
                catalogVideo.setSongWriter(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("discNum".equals(currentName)) {
                catalogVideo.setDiscNum(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("lyricist".equals(currentName)) {
                catalogVideo.setLyricist(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumArtistAsin".equals(currentName)) {
                catalogVideo.setAlbumArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artist".equals(currentName)) {
                catalogVideo.setArtist(RelatedArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogVideo.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("albumAsin".equals(currentName)) {
                catalogVideo.setAlbumAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("assetQuality".equals(currentName)) {
                catalogVideo.setAssetQuality(AssetQualitiesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                catalogVideo.setGenre(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogVideo.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                catalogVideo.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("performer".equals(currentName)) {
                catalogVideo.setPerformer(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogVideo;
    }
}
