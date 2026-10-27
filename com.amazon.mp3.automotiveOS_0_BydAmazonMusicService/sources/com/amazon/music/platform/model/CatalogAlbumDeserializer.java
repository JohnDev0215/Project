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
public class CatalogAlbumDeserializer extends JsonDeserializer<CatalogAlbum> {
    public static final CatalogAlbumDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogAlbumDeserializer catalogAlbumDeserializer = new CatalogAlbumDeserializer();
        INSTANCE = catalogAlbumDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogAlbumDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogAlbum.class, catalogAlbumDeserializer);
    }

    private CatalogAlbumDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogAlbum deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogAlbum".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogAlbum deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogAlbum catalogAlbum = new CatalogAlbum();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("artistAsin".equals(currentName)) {
                catalogAlbum.setArtistAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                catalogAlbum.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogAlbum.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                catalogAlbum.setGenre(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                catalogAlbum.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("languageOfPerformances".equals(currentName)) {
                catalogAlbum.setLanguageOfPerformances(LanguageOfPerformancesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                catalogAlbum.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("releaseDate".equals(currentName)) {
                catalogAlbum.setReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                catalogAlbum.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogAlbum.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                catalogAlbum.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                catalogAlbum.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                catalogAlbum.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogAlbum.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artistName".equals(currentName)) {
                catalogAlbum.setArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                catalogAlbum.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                catalogAlbum.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                catalogAlbum.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                catalogAlbum.setAlternativeName(AlbumNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                catalogAlbum.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogAlbum.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artist".equals(currentName)) {
                catalogAlbum.setArtist(RelatedArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                catalogAlbum.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogAlbum.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogAlbum.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("version".equals(currentName)) {
                catalogAlbum.setVersion(VersionsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("titleSetAsin".equals(currentName)) {
                catalogAlbum.setTitleSetAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contributorAsin".equals(currentName)) {
                catalogAlbum.setContributorAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("languageOfPerformance".equals(currentName)) {
                catalogAlbum.setLanguageOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogAlbum.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogAlbum;
    }
}
