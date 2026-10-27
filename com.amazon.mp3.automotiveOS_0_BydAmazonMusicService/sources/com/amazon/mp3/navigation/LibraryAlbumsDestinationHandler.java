package com.amazon.mp3.navigation;

import android.net.Uri;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.catalog.fragment.LibraryAlbumsFragment;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.music.destination.LibraryAlbumsDestination;

/* JADX INFO: loaded from: classes3.dex */
public class LibraryAlbumsDestinationHandler extends AbstractLibraryDestinationHandler<LibraryAlbumsDestination> {
    @Override // com.amazon.mp3.navigation.AbstractLibraryDestinationHandler
    protected Uri getContentURI(final String sourceId) {
        if (AmazonApplication.getCapabilities().isBauhausLibraryEnabled()) {
            return LibraryAlbumsFragment.getUri();
        }
        return MediaProvider.Albums.getFilterContentUri(sourceId, "");
    }
}
