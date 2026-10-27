package com.amazon.mp3.playlist.service;

import com.amazon.musicplaylist.model.AppendTracksToPlaylistCall;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistRequest;
import com.amazon.musicplaylist.model.AppendTracksToPlaylistResponse;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistService {
    private static final ObjectMapper MAPPER = Mappers.getMapper();
    private final Configuration mConfiguration;

    PlaylistService(final Configuration configuration) {
        this.mConfiguration = configuration;
    }

    public AppendTracksToPlaylistResponse appendTracksToPlaylists(AppendTracksToPlaylistRequest request) throws VolleyError {
        return new AppendTracksToPlaylistCall(this.mConfiguration.getEndpoint(), request, this.mConfiguration.getRequestInterceptor()).execute(MAPPER);
    }
}
