package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class RemoveTracksFromPlaylistCall extends CoralCall<RemoveTracksFromPlaylistRequest, RemoveTracksFromPlaylistResponse> {
    public RemoveTracksFromPlaylistCall(URL url, RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest, RequestInterceptor requestInterceptor) {
        this(url, removeTracksFromPlaylistRequest, requestInterceptor, false);
    }

    public RemoveTracksFromPlaylistCall(URL url, RemoveTracksFromPlaylistRequest removeTracksFromPlaylistRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, removeTracksFromPlaylistRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new RemoveTracksFromPlaylistApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<RemoveTracksFromPlaylistResponse> getResponseType() {
        return RemoveTracksFromPlaylistResponse.class;
    }
}
