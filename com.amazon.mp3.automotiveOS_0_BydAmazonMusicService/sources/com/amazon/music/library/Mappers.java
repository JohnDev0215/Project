package com.amazon.music.library;

import com.amazon.cirrus.libraryservice.v3.GetAlbumsRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetAlbumsResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetAlphabetsForScrollingRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetAlphabetsForScrollingResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetArtistsRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetArtistsResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetGenresRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetGenresResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetLibraryCountsRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetLibraryCountsResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetTracksByIdRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetTracksByIdResponseDeserializer;
import com.amazon.cirrus.libraryservice.v3.GetTracksRequestSerializer;
import com.amazon.cirrus.libraryservice.v3.GetTracksResponseDeserializer;
import com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryRequestSerializer;
import com.amazon.musicplaylist.model.GetFollowedPlaylistsInLibraryResponseDeserializer;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryRequestSerializer;
import com.amazon.musicplaylist.model.GetOwnedPlaylistsInLibraryResponseDeserializer;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2RequestSerializer;
import com.amazon.musicplaylist.model.GetPlaylistsByIdV2ResponseDeserializer;
import com.amazon.musicplaylist.model.SociallySharePlaylistRequestSerializer;
import com.amazon.musicplaylist.model.SociallySharePlaylistResponseDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes4.dex */
final class Mappers {
    private static final ObjectMapper MAPPER;

    Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        MAPPER = objectMapper;
        GetPlaylistsByIdV2RequestSerializer.register(objectMapper);
        GetPlaylistsByIdV2ResponseDeserializer.register(objectMapper);
        GetOwnedPlaylistsInLibraryRequestSerializer.register(objectMapper);
        GetOwnedPlaylistsInLibraryResponseDeserializer.register(objectMapper);
        GetFollowedPlaylistsInLibraryRequestSerializer.register(objectMapper);
        GetFollowedPlaylistsInLibraryResponseDeserializer.register(objectMapper);
        GetAlbumsRequestSerializer.register(objectMapper);
        GetAlbumsResponseDeserializer.register(objectMapper);
        GetAlphabetsForScrollingRequestSerializer.register(objectMapper);
        GetAlphabetsForScrollingResponseDeserializer.register(objectMapper);
        GetArtistsRequestSerializer.register(objectMapper);
        GetArtistsResponseDeserializer.register(objectMapper);
        GetGenresRequestSerializer.register(objectMapper);
        GetGenresResponseDeserializer.register(objectMapper);
        GetLibraryCountsRequestSerializer.register(objectMapper);
        GetLibraryCountsResponseDeserializer.register(objectMapper);
        GetTracksByIdRequestSerializer.register(objectMapper);
        GetTracksByIdResponseDeserializer.register(objectMapper);
        GetTracksRequestSerializer.register(objectMapper);
        GetTracksResponseDeserializer.register(objectMapper);
        SociallySharePlaylistRequestSerializer.register(objectMapper);
        SociallySharePlaylistResponseDeserializer.register(objectMapper);
    }

    static ObjectMapper getMapper() {
        return MAPPER;
    }
}
