package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class AppendTracksToPlaylistCall extends CoralCall<AppendTracksToPlaylistRequest, AppendTracksToPlaylistResponse> {
    public AppendTracksToPlaylistCall(URL url, AppendTracksToPlaylistRequest appendTracksToPlaylistRequest, RequestInterceptor requestInterceptor) {
        this(url, appendTracksToPlaylistRequest, requestInterceptor, false);
    }

    public AppendTracksToPlaylistCall(URL url, AppendTracksToPlaylistRequest appendTracksToPlaylistRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, appendTracksToPlaylistRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new AppendTracksToPlaylistApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<AppendTracksToPlaylistResponse> getResponseType() {
        return AppendTracksToPlaylistResponse.class;
    }
}
