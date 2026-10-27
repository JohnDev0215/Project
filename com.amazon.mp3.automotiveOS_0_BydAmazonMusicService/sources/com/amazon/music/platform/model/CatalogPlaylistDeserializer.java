package com.amazon.music.platform.model;

import com.amazon.cloud9.jackson.SimpleDeserializers;
import com.amazon.music.destination.parser.ParserUtil;
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

/* JADX INFO: loaded from: classes4.dex */
public class CatalogPlaylistDeserializer extends JsonDeserializer<CatalogPlaylist> {
    public static final CatalogPlaylistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogPlaylistDeserializer catalogPlaylistDeserializer = new CatalogPlaylistDeserializer();
        INSTANCE = catalogPlaylistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogPlaylistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogPlaylist.class, catalogPlaylistDeserializer);
    }

    private CatalogPlaylistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogPlaylist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogPlaylist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogPlaylist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogPlaylist catalogPlaylist = new CatalogPlaylist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("languageOfPerformances".equals(currentName)) {
                catalogPlaylist.setLanguageOfPerformances(LanguageOfPerformancesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primeStatus".equals(currentName)) {
                catalogPlaylist.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("track".equals(currentName)) {
                catalogPlaylist.setTrack(RelatedTracksDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                catalogPlaylist.setGenre(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seriesAsin".equals(currentName)) {
                catalogPlaylist.setSeriesAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                catalogPlaylist.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                catalogPlaylist.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogPlaylist.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                catalogPlaylist.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogPlaylist.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curator".equals(currentName)) {
                catalogPlaylist.setCurator(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogPlaylist.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("era".equals(currentName)) {
                catalogPlaylist.setEra(ErasDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                catalogPlaylist.setAlternativeName(PlaylistNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                catalogPlaylist.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogPlaylist.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("mood".equals(currentName)) {
                catalogPlaylist.setMood(MoodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogPlaylist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                catalogPlaylist.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogPlaylist.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogPlaylist.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("languageOfPerformance".equals(currentName)) {
                catalogPlaylist.setLanguageOfPerformance(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                catalogPlaylist.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("marketplaceId".equals(currentName)) {
                catalogPlaylist.setMarketplaceId(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                catalogPlaylist.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                catalogPlaylist.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistType".equals(currentName)) {
                catalogPlaylist.setPlaylistType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                catalogPlaylist.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                catalogPlaylist.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                catalogPlaylist.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogPlaylist;
    }
}
