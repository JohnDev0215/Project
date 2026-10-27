package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.download.helper.PrimePlaylistTrackDownloadHelper;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
public class CatalogPlaylistTrackMigrationHelper extends TrackMigrationHelper {
    private PrimePlaylistTrackDownloadHelper mPrimePlaylistTrackDownloadHelper;

    public CatalogPlaylistTrackMigrationHelper(final Context context) {
        super(context);
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.TrackMigrationHelper, com.amazon.mp3.library.provider.source.cirrus.dbupgrade.TrackMigrator
    public Uri migrateTrack(final PreUnityTrack preUnityTrack) throws IOException {
        return migrateCatalogPlaylistTrack(preUnityTrack, getPrimePlaylistTrackDownloadHelper());
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.TrackMigrationHelper
    boolean isPrimeTrackDownloaded(final String asin) {
        return getPrimePlaylistTrackDownloadHelper().isTrackDownloaded(asin);
    }

    private PrimePlaylistTrackDownloadHelper getPrimePlaylistTrackDownloadHelper() {
        if (this.mPrimePlaylistTrackDownloadHelper == null) {
            this.mPrimePlaylistTrackDownloadHelper = new PrimePlaylistTrackDownloadHelper(getContext(), StorageLocationFileManagerProvider.getInstance(getContext()).getFileManager());
        }
        return this.mPrimePlaylistTrackDownloadHelper;
    }
}
