package com.amazon.music.library;

import com.amazon.music.library.AbstractLibraryServiceImpl;
import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes4.dex */
public final class LibraryServiceImpl extends AbstractLibraryServiceImpl<AbstractLibraryServiceImpl.Configuration> {
    @Override // com.amazon.music.library.AbstractLibraryServiceImpl, com.amazon.music.library.LibraryService
    public /* bridge */ /* synthetic */ com.amazon.musicplaylist.model.SociallySharePlaylistResponse sociallySharePlaylist(com.amazon.musicplaylist.model.SociallySharePlaylistRequest sociallySharePlaylistRequest) throws VolleyError {
        return super.sociallySharePlaylist(sociallySharePlaylistRequest);
    }

    public LibraryServiceImpl(AbstractLibraryServiceImpl.Configuration configuration) {
        super(configuration);
    }
}
