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
public class PlaylistDeserializer extends JsonDeserializer<Playlist> {
    public static final PlaylistDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        PlaylistDeserializer playlistDeserializer = new PlaylistDeserializer();
        INSTANCE = playlistDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.musicensembleservice.brush.PlaylistDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Playlist.class, playlistDeserializer);
    }

    private PlaylistDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Playlist deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.musicensembleservice.brush#Playlist".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Playlist deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        Playlist playlist = new Playlist();
        while (jsonParser.getCurrentToken() != JsonToken.END_OBJECT) {
            if (jsonParser.getCurrentToken() != JsonToken.FIELD_NAME) {
                throw new JsonParseException(jsonParser, "Expected field name, got " + jsonParser.getCurrentToken(), jsonParser.getTokenLocation());
            }
            String currentName = jsonParser.getCurrentName();
            if (jsonParser.nextToken() == null) {
                throw new JsonParseException(jsonParser, "Unexpected end of input", jsonParser.getTokenLocation());
            }
            if ("primaryTitle".equals(currentName)) {
                playlist.setPrimaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (MediaTrack.ROLE_DESCRIPTION.equals(currentName)) {
                playlist.setDescription(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("rankedIndex".equals(currentName)) {
                playlist.setRankedIndex(SimpleDeserializers.deserializeInteger(jsonParser, deserializationContext));
            } else if ("asin".equals(currentName)) {
                playlist.setAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("playmodeEligibility".equals(currentName)) {
                playlist.setPlaymodeEligibility(PlaymodeEligibilityDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("expirationTime".equals(currentName)) {
                playlist.setExpirationTime(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("contributors".equals(currentName)) {
                playlist.setContributors(StringsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("secondaryTitle".equals(currentName)) {
                playlist.setSecondaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("artists".equals(currentName)) {
                playlist.setArtists(ArtistsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("spiritualCategory".equals(currentName)) {
                playlist.setSpiritualCategory(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.IMAGE.equals(currentName)) {
                playlist.setImage(ImageDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("title".equals(currentName)) {
                playlist.setTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("curator".equals(currentName)) {
                playlist.setCurator(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isInLibrary".equals(currentName)) {
                playlist.setIsInLibrary(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if (ContextMappingConstants.LABEL.equals(currentName)) {
                playlist.setLabel(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("parentalControls".equals(currentName)) {
                playlist.setParentalControls(ParentalControlsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("contentTypes".equals(currentName)) {
                playlist.setContentTypes(ContentTypesDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("duration".equals(currentName)) {
                playlist.setDuration(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("tracksCount".equals(currentName)) {
                playlist.setTracksCount(SimpleDeserializers.deserializeLong(jsonParser, deserializationContext));
            } else if ("seriesAsin".equals(currentName)) {
                playlist.setSeriesAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tracks".equals(currentName)) {
                playlist.setTracks(TracksDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("tertiaryTitle".equals(currentName)) {
                playlist.setTertiaryTitle(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("contentTiers".equals(currentName)) {
                playlist.setContentTiers(ContentTiersDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else if ("blockRef".equals(currentName)) {
                playlist.setBlockRef(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("isOwned".equals(currentName)) {
                playlist.setIsOwned(SimpleDeserializers.deserializeBoolean(jsonParser, deserializationContext));
            } else if ("requestedAsin".equals(currentName)) {
                playlist.setRequestedAsin(SimpleDeserializers.deserializeString(jsonParser, deserializationContext));
            } else if ("tags".equals(currentName)) {
                playlist.setTags(TagsDeserializer.INSTANCE.deserialize(jsonParser, deserializationContext));
            } else {
                jsonParser.skipChildren();
            }
            jsonParser.nextToken();
        }
        return playlist;
    }
}
