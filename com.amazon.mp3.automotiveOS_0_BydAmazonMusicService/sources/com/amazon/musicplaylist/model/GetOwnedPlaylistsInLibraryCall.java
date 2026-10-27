package com.amazon.musicplaylist.model;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import java.net.URL;

/* JADX INFO: loaded from: classes5.dex */
public class GetOwnedPlaylistsInLibraryCall extends CoralCall<GetOwnedPlaylistsInLibraryRequest, GetOwnedPlaylistsInLibraryResponse> {
    public GetOwnedPlaylistsInLibraryCall(URL url, GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest, RequestInterceptor requestInterceptor) {
        this(url, getOwnedPlaylistsInLibraryRequest, requestInterceptor, false);
    }

    public GetOwnedPlaylistsInLibraryCall(URL url, GetOwnedPlaylistsInLibraryRequest getOwnedPlaylistsInLibraryRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getOwnedPlaylistsInLibraryRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetOwnedPlaylistsInLibraryApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetOwnedPlaylistsInLibraryResponse> getResponseType() {
        return GetOwnedPlaylistsInLibraryResponse.class;
    }
}
