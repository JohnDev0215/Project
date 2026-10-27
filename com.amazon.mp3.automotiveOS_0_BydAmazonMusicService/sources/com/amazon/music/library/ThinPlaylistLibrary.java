package com.amazon.music.library;

import com.amazon.music.library.SociallySharePlaylistRequest;
import com.amazon.music.library.ThinSociallySharePlaylistRequest;
import com.amazon.music.library.ThinSociallySharePlaylistResponse;

/* JADX INFO: loaded from: classes4.dex */
final class ThinPlaylistLibrary implements PlaylistLibrary<Object, ThinSociallySharePlaylistRequest> {
    private final LibraryService mService;

    public ThinPlaylistLibrary(LibraryService libraryService) {
        this.mService = libraryService;
    }

    @Override // com.amazon.music.library.PlaylistLibrary
    public SociallySharePlaylistResponse sociallySharePlaylist(ThinSociallySharePlaylistRequest thinSociallySharePlaylistRequest) throws Exception {
        return new ThinSociallySharePlaylistResponse.Builder().withMetadata(this.mService.sociallySharePlaylist(thinSociallySharePlaylistRequest.get())).build();
    }

    @Override // com.amazon.music.library.PlaylistLibrary
    public SociallySharePlaylistRequest.Builder getSociallyShareRequestBuilder() {
        return new ThinSociallySharePlaylistRequest.Builder();
    }
}
