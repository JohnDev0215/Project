package com.amazon.mp3.navigation;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.LibraryArtistsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.LibraryArtistsDestination;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryArtistsDestinationHandler extends AbstractLibraryDestinationHandler<LibraryArtistsDestination> {
    @Override // com.amazon.mp3.navigation.AbstractLibraryDestinationHandler
    protected Uri getContentURI(final String sourceId) {
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            return LibraryArtistsFragment.getUri();
        }
        return MediaProvider.Artists.getFilterContentUri(sourceId, "");
    }
}
