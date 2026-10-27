package com.amazon.music.library;

import com.amazon.music.library.SociallySharePlaylistRequest;

/* JADX INFO: loaded from: classes4.dex */
public interface PlaylistLibrary<TPlaylistsRequest, TSociallySharePlaylistRequest extends SociallySharePlaylistRequest> {
    SociallySharePlaylistRequest.Builder getSociallyShareRequestBuilder();

    SociallySharePlaylistResponse sociallySharePlaylist(TSociallySharePlaylistRequest tsociallyshareplaylistrequest) throws Exception;
}
