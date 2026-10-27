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
public class AlbumDeserializer extends JsonDeserializer<Album> {
    public static final AlbumDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        AlbumDeserializer albumDeserializer = new AlbumDeserializer();
        INSTANCE = albumDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.AlbumDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Album.class, albumDeserializer);
    }

    private AlbumDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Album deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#Album".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Album deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Album album = new Album();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primaryTitle".equals(currentName)) {
                album.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("versions".equals(currentName)) {
                album.setVersions(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                album.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("localOriginalReleaseDate".equals(currentName)) {
                album.setLocalOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("releaseType".equals(currentName)) {
                album.setReleaseType(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                album.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_COMMENTARY.equals(currentName)) {
                album.setCommentary(CommentaryDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("expirationTime".equals(currentName)) {
                album.setExpirationTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("isOwned".equals(currentName)) {
                album.setIsOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("tracks".equals(currentName)) {
                album.setTracks(TracksDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                album.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tracksCount".equals(currentName)) {
                album.setTracksCount(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("copyright".equals(currentName)) {
                album.setCopyright(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                album.setDuration(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("titleSetAsin".equals(currentName)) {
                album.setTitleSetAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTiers".equals(currentName)) {
                album.setContentTiers(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("concatenatedArtistName".equals(currentName)) {
                album.setConcatenatedArtistName(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("originalReleaseDate".equals(currentName)) {
                album.setOriginalReleaseDate(SimpleDeserializers.deserializeDate(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                album.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                album.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("hasCommentary".equals(currentName)) {
                album.setHasCommentary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                album.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("localTimeZone".equals(currentName)) {
                album.setLocalTimeZone(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                album.setArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                album.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                album.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.CONTENT_ENCODING.equals(currentName)) {
                album.setContentEncoding(ContentEncodingsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                album.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                album.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                album.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                album.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                album.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                album.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tags".equals(currentName)) {
                album.setTags(TagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return album;
    }
}
