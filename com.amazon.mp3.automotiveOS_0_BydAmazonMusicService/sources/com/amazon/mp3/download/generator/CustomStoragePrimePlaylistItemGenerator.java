package com.amazon.mp3.download.generator;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;

/* JADX INFO: loaded from: classes3.dex */
public class CustomStoragePrimePlaylistItemGenerator extends PrimePlaylistItemGenerator {
    private final StorageLocation mStorageLocation;

    public CustomStoragePrimePlaylistItemGenerator(final Context context, final String playlistName, final StorageLocationFileManager storageLocationFileManager, StorageLocation storageLocation) {
        super(context, playlistName, storageLocationFileManager);
        this.mStorageLocation = storageLocation;
    }

    @Override // com.amazon.mp3.download.generator.PrimePlaylistItemGenerator
    @NonNull
    protected String getTrackDestinationUri(final String asin, final String title, final String album, final String albumArtist, final String artist, final String trackNum) {
        return FileNameGenerator.generatePathForStorageLocation(this.mContext, album, artist, albumArtist, asin, title, null, trackNum, null, true, true, this.mStorageLocationFileManager, this.mStorageLocation);
    }
}
