package com.amazon.music.platform.model;

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
public class DocumentDeserializer extends JsonDeserializer<Document> {
    public static final DocumentDeserializer INSTANCE;
    public static final SimpleModule MODULE;

    static {
        DocumentDeserializer documentDeserializer = new DocumentDeserializer();
        INSTANCE = documentDeserializer;
        SimpleModule simpleModule = new SimpleModule("com.amazon.music.platform.model.DocumentDeserializer", new Version(1, 0, 0, null, null, null));
        MODULE = simpleModule;
        simpleModule.addDeserializer(Document.class, documentDeserializer);
    }

    private DocumentDeserializer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    @CheckForNull
    public Document deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
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
            if ("com.amazon.music.platform.model#LiveEvent".equals(text)) {
                return LiveEventDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogLyric".equals(text)) {
                return CatalogLyricDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Lyric".equals(text)) {
                return LyricDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryGenre".equals(text)) {
                return LibraryGenreDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogGenre".equals(text)) {
                return CatalogGenreDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Genre".equals(text)) {
                return GenreDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogVideo".equals(text)) {
                return CatalogVideoDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LiveSportsCompetition".equals(text)) {
                return LiveSportsCompetitionDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LiveSportsProgram".equals(text)) {
                return LiveSportsProgramDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LiveSportsDocument".equals(text)) {
                return LiveSportsDocumentDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogVideoPlaylist".equals(text)) {
                return CatalogVideoPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogPlaylist".equals(text)) {
                return CatalogPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CommunityPlaylist".equals(text)) {
                return CommunityPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryPlaylist".equals(text)) {
                return LibraryPlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Playlist".equals(text)) {
                return PlaylistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogPodcastShow".equals(text)) {
                return CatalogPodcastShowDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryArtist".equals(text)) {
                return LibraryArtistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogArtist".equals(text)) {
                return CatalogArtistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Artist".equals(text)) {
                return ArtistDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogAlbum".equals(text)) {
                return CatalogAlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryAlbum".equals(text)) {
                return LibraryAlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Album".equals(text)) {
                return AlbumDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogStation".equals(text)) {
                return CatalogStationDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Station".equals(text)) {
                return StationDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogPodcastEpisode".equals(text)) {
                return CatalogPodcastEpisodeDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#CatalogTrack".equals(text)) {
                return CatalogTrackDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#LibraryTrack".equals(text)) {
                return LibraryTrackDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Track".equals(text)) {
                return TrackDeserializer.INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            if ("com.amazon.music.platform.model#Document".equals(text)) {
                return INSTANCE.deserializeFields(jsonParser, deserializationContext);
            }
            throw new UnsupportedOperationException("Deserialization for the Sub-Type not supported: " + text);
        }
        return deserializeFields(jsonParser, deserializationContext);
    }

    public Document deserializeFields(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        throw new UnsupportedOperationException("Deserialization for abstract type not supported");
    }
}
