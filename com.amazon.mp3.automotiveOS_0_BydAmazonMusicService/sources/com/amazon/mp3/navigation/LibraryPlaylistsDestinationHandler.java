package com.amazon.mp3.navigation;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.LibraryPlaylistsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.LibraryPlaylistsDestination;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryPlaylistsDestinationHandler extends AbstractLibraryDestinationHandler<LibraryPlaylistsDestination> {
    @Override // com.amazon.mp3.navigation.AbstractLibraryDestinationHandler
    protected Uri getContentURI(final String sourceId) {
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            return LibraryPlaylistsFragment.getUri();
        }
        return MediaProvider.Playlists.getFilterContentUri(sourceId, "");
    }
}
