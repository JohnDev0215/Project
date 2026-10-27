package com.amazon.mp3.navigation;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.LibraryTracksFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.LibraryTracksDestination;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryTracksDestinationHandler extends AbstractLibraryDestinationHandler<LibraryTracksDestination> {
    @Override // com.amazon.mp3.navigation.AbstractLibraryDestinationHandler
    protected Uri getContentURI(final String sourceId) {
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            return LibraryTracksFragment.getUri();
        }
        return MediaProvider.Tracks.getFilterContentUri(sourceId, "");
    }
}
