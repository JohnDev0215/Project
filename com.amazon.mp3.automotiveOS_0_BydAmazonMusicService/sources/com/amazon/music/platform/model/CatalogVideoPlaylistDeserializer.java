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
import com.google.android.gms.cast.MediaTrack;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogVideoPlaylistDeserializer extends JsonDeserializer<CatalogVideoPlaylist> {
    public static final CatalogVideoPlaylistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        CatalogVideoPlaylistDeserializer catalogVideoPlaylistDeserializer = new CatalogVideoPlaylistDeserializer();
        INSTANCE = catalogVideoPlaylistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.CatalogVideoPlaylistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(CatalogVideoPlaylist.class, catalogVideoPlaylistDeserializer);
    }

    private CatalogVideoPlaylistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public CatalogVideoPlaylist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#CatalogVideoPlaylist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public CatalogVideoPlaylist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        CatalogVideoPlaylist catalogVideoPlaylist = new CatalogVideoPlaylist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primeStatus".equals(currentName)) {
                catalogVideoPlaylist.setPrimeStatus(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                catalogVideoPlaylist.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("curator".equals(currentName)) {
                catalogVideoPlaylist.setCurator(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artMedium".equals(currentName)) {
                catalogVideoPlaylist.setArtMedium(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artSmall".equals(currentName)) {
                catalogVideoPlaylist.setArtSmall(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("primaryGenre".equals(currentName)) {
                catalogVideoPlaylist.setPrimaryGenre(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artTiny".equals(currentName)) {
                catalogVideoPlaylist.setArtTiny(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("isMusicSubscription".equals(currentName)) {
                catalogVideoPlaylist.setIsMusicSubscription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ParserUtil.GENRE_SEGMENT_NAME.equals(currentName)) {
                catalogVideoPlaylist.setGenre(GenresDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("mood".equals(currentName)) {
                catalogVideoPlaylist.setMood(MoodsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("seriesAsin".equals(currentName)) {
                catalogVideoPlaylist.setSeriesAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artLarge".equals(currentName)) {
                catalogVideoPlaylist.setArtLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artOriginal".equals(currentName)) {
                catalogVideoPlaylist.setArtOriginal(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                catalogVideoPlaylist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                catalogVideoPlaylist.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                catalogVideoPlaylist.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTier".equals(currentName)) {
                catalogVideoPlaylist.setContentTier(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (voMimeTypes.VOBASE_TYPE_VIDEO.equals(currentName)) {
                catalogVideoPlaylist.setVideo(RelatedTracksDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("trackCount".equals(currentName)) {
                catalogVideoPlaylist.setTrackCount(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("era".equals(currentName)) {
                catalogVideoPlaylist.setEra(ErasDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (Splash.PARAMS_MUSIC_TERRITORY.equals(currentName)) {
                catalogVideoPlaylist.setMusicTerritory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                catalogVideoPlaylist.setDuration(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("artFull".equals(currentName)) {
                catalogVideoPlaylist.setArtFull(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("playlistType".equals(currentName)) {
                catalogVideoPlaylist.setPlaylistType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("alternativeName".equals(currentName)) {
                catalogVideoPlaylist.setAlternativeName(PlaylistNamesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("artExtraLarge".equals(currentName)) {
                catalogVideoPlaylist.setArtExtraLarge(ArtDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return catalogVideoPlaylist;
    }
}
