package com.amazon.mp3.library.provider.source.cirrus.dbupgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.download.generator.CustomStorageItemGenerator;
import com.amazon.mp3.download.generator.CustomStoragePrimePlaylistItemGenerator;
import com.amazon.mp3.download.generator.PrimePlaylistItemGenerator;
import com.amazon.mp3.download.helper.TrackDownloadHelper;
import com.amazon.mp3.download.helper.UdoPlaylistDownloadHelper;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.util.TrackUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.util.FileUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX INFO: loaded from: classes3.dex */
class TrackMigrationHelper implements TrackMigrator {
    private static final String TAG = "TrackMigrationHelper";
    private final Context context;
    private TrackDownloadHelper mTrackDownloadHelper;

    public TrackMigrationHelper(final Context context) {
        this.context = context;
    }

    @Override // com.amazon.mp3.library.provider.source.cirrus.dbupgrade.TrackMigrator
    public Uri migrateTrack(final PreUnityTrack preUnityTrack) throws IOException {
        if (preUnityTrack.isSideloaded()) {
            return migrateSideloadedTrack(preUnityTrack);
        }
        if (preUnityTrack.isOwned() && preUnityTrack.asin != null) {
            File file = new File(preUnityTrack.localUri);
            if (!file.exists()) {
                Log.error(TAG, "The preUnityTrack file is not found: " + file.getAbsolutePath());
                return null;
            }
            return getMigratedTrackContentUri(preUnityTrack);
        }
        if (preUnityTrack.isCatalogTrack()) {
            return migrateCatalogTrack(preUnityTrack, getTrackDownloadHelper());
        }
        if (preUnityTrack.isUploadedToCloud()) {
            return migrateUploadedToCloudTrack(preUnityTrack);
        }
        Log.error(TAG, "migrateTrack failed: Unknown track type: " + preUnityTrack);
        return null;
    }

    private Uri migrateSideloadedTrack(final PreUnityTrack preUnityTrack) {
        String str = TAG;
        Log.verbose(str, "migrateSideloadedTrack: " + preUnityTrack);
        if (preUnityTrack.localUri != null) {
            Log.verbose(str, "Migrating sideloaded track: " + preUnityTrack);
            Uri uriFindLocalTrackByTrackFileLocationUri = new UdoPlaylistDownloadHelper(this.context).findLocalTrackByTrackFileLocationUri(CirrusDatabase.getReadOnlyDatabase(this.context), preUnityTrack.localUri);
            if (uriFindLocalTrackByTrackFileLocationUri != null) {
                MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(uriFindLocalTrackByTrackFileLocationUri);
                if (track != null) {
                    Log.verbose(str, "The track is migrated: " + track);
                    return uriFindLocalTrackByTrackFileLocationUri;
                }
            } else {
                Log.verbose(str, "The track is not found in the library: " + preUnityTrack);
            }
        }
        Log.verbose(str, "The track is not migrated: " + preUnityTrack);
        return null;
    }

    private Uri migrateUploadedToCloudTrack(final PreUnityTrack preUnityTrack) throws IOException {
        String str = TAG;
        Log.verbose(str, "migrateUploadedToCloudTrack: " + preUnityTrack);
        Uri migratedTrackContentUri = getMigratedTrackContentUri(preUnityTrack);
        if (migratedTrackContentUri != null) {
            Log.verbose(str, "The track is migrated already: " + preUnityTrack + ", trackContentUri: " + migratedTrackContentUri);
            return migratedTrackContentUri;
        }
        Uri contentUri = MediaProvider.Tracks.getContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(preUnityTrack.source), preUnityTrack.luid);
        if (contentUri == null) {
            Log.error(str, "Unable to create a track uri for " + preUnityTrack);
            return null;
        }
        Item itemCreateItem = createItem(contentUri, getStorageLocation(preUnityTrack));
        if (itemCreateItem == null) {
            Log.error(str, "Could not create an Item for trackUri: " + contentUri + ", " + preUnityTrack);
            return null;
        }
        return migrateTrackItem(preUnityTrack, getTrackDownloadHelper(), itemCreateItem);
    }

    private Item createItem(final Uri trackUri, final StorageLocation storageLocation) {
        return new CustomStorageItemGenerator(this.context, getStorageLocationFileManager(), storageLocation).generate(trackUri, false, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), 5);
    }

    private Uri migrateCatalogTrack(final PreUnityTrack preUnityTrack, final TrackDownloadHelper trackDownloadHelper) throws IOException {
        String str = TAG;
        Log.verbose(str, "migrateCatalogTrack: " + preUnityTrack);
        if (isPrimeTrackDownloaded(preUnityTrack.asin)) {
            Log.verbose(str, "The track is migrated already: " + preUnityTrack.asin + ", " + preUnityTrack.title + ", " + preUnityTrack.artist);
            return getMigratedTrackContentUri(preUnityTrack);
        }
        Item itemFindTrackItemByAsin = findTrackItemByAsin(this.context, preUnityTrack.asin, preUnityTrack.source, getStorageLocation(preUnityTrack));
        if (itemFindTrackItemByAsin == null) {
            Log.error(str, "migrateCatalogTrack: Could not create an Item for: " + preUnityTrack);
            return null;
        }
        return migrateTrackItem(preUnityTrack, trackDownloadHelper, itemFindTrackItemByAsin);
    }

    Uri migrateCatalogPlaylistTrack(final PreUnityTrack preUnityTrack, final TrackDownloadHelper trackDownloadHelper) throws Throwable {
        String str = TAG;
        Log.verbose(str, "migrateCatalogPlaylistTrack: " + preUnityTrack);
        if (trackDownloadHelper.isTrackDownloaded(preUnityTrack.asin)) {
            Log.verbose(str, "The track is migrated already: " + preUnityTrack.asin + ", " + preUnityTrack.title + ", " + preUnityTrack.artist);
            return MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus-local", "primeplaylist", preUnityTrack.asin);
        }
        Item itemFindPlaylistTrackItemByAsin = findPlaylistTrackItemByAsin(this.context, preUnityTrack.asin, preUnityTrack.source, getStorageLocation(preUnityTrack));
        if (itemFindPlaylistTrackItemByAsin == null) {
            Log.error(str, "migrateCatalogPlaylistTrack: Could not create an Item for: " + preUnityTrack);
            return null;
        }
        if (getTrackDownloadHelper().isTrackDownloaded(preUnityTrack.asin)) {
            MusicTrack track = AmazonApplication.getLibraryItemFactory().getTrack(TrackUtil.findLocalTrackContentUriByAsin(this.context, preUnityTrack.asin));
            if (track == null) {
                Log.error(str, "The local track is not found: " + preUnityTrack.asin);
                return null;
            }
            Log.verbose(str, "Copying a migrated track to the PrimePlaylist folder: " + track.getLocalUri() + " => " + itemFindPlaylistTrackItemByAsin.getDestinationUri());
            FileUtil.copyFile(track.getLocalUri(), itemFindPlaylistTrackItemByAsin.getDestinationUri(), false);
            if (!new File(itemFindPlaylistTrackItemByAsin.getDestinationUri()).canRead()) {
                Log.error(str, "Unable to copy the track from: " + track.getLocalUri() + " to " + itemFindPlaylistTrackItemByAsin.getDestinationUri());
                return null;
            }
            updateTrackDownloadState(trackDownloadHelper, itemFindPlaylistTrackItemByAsin);
            Uri migratedTrackContentUri = getMigratedTrackContentUri(preUnityTrack);
            logMigrationStatus(preUnityTrack, itemFindPlaylistTrackItemByAsin, migratedTrackContentUri);
            return migratedTrackContentUri;
        }
        return migrateTrackItem(preUnityTrack, trackDownloadHelper, itemFindPlaylistTrackItemByAsin);
    }

    private Uri migrateTrackItem(final PreUnityTrack preUnityTrack, final TrackDownloadHelper trackDownloadHelper, final Item item) throws IOException {
        String str = TAG;
        Log.verbose(str, "migrateTrackItem: " + preUnityTrack + ", " + item);
        if (item == null) {
            Log.error(str, "Item is null");
            return null;
        }
        if (!moveTrackFile(preUnityTrack, item)) {
            Log.error(str, "Unable to move " + preUnityTrack.localUri + " to " + item.getDestinationUri());
            return null;
        }
        updateTrackDownloadState(trackDownloadHelper, item);
        Uri migratedTrackContentUri = getMigratedTrackContentUri(preUnityTrack);
        logMigrationStatus(preUnityTrack, item, migratedTrackContentUri);
        return migratedTrackContentUri;
    }

    private void logMigrationStatus(final PreUnityTrack preUnityTrack, final Item item, final Uri trackContentUri) {
        if (trackContentUri == null) {
            Log.warning(TAG, "Could not migrate track: " + preUnityTrack + ", item: " + item);
            return;
        }
        Log.verbose(TAG, "The track is migrated: " + item.getAsin() + ", " + preUnityTrack.title + ", " + preUnityTrack.artist + ", " + item.getDestinationUri() + ", luid: " + item.getLuid() + ", Local track content uri: " + trackContentUri);
    }

    private void updateTrackDownloadState(final TrackDownloadHelper trackDownloadHelper, final Item item) {
        trackDownloadHelper.updateDownloadState(item.getLuid() != null ? item.getLuid() : item.getAsin(), item, DownloadState.DOWNLOADED);
    }

    Item findTrackItemByAsin(final Context context, final String asin, final int source, final StorageLocation storageLocation) {
        Item itemGenerate;
        Uri uriFindCollectionUriByAsin = new QuerySongByAsin(context).findCollectionUriByAsin(asin);
        String str = TAG;
        Log.verbose(str, "findTrackItemByAsin: " + asin + ", source: " + source + ", trackUri: " + uriFindCollectionUriByAsin);
        if (uriFindCollectionUriByAsin != null) {
            itemGenerate = createItem(uriFindCollectionUriByAsin, storageLocation);
        } else {
            uriFindCollectionUriByAsin = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(source), "primeplaylist", asin);
            PrimePlaylistItemGenerator primePlaylistItemGenerator = new PrimePlaylistItemGenerator(context, "", getStorageLocationFileManager());
            Log.verbose(str, "PrimePlaylistToTrackTable - findTrackItemByAsin: " + asin + ", source: " + source + ", trackUri: " + uriFindCollectionUriByAsin);
            itemGenerate = primePlaylistItemGenerator.generate(uriFindCollectionUriByAsin, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), null);
        }
        Log.verbose(str, "findTrackItemByAsin: " + asin + ", source: " + source + ", trackUri: " + uriFindCollectionUriByAsin + ", item: " + itemGenerate);
        return itemGenerate;
    }

    Item findPlaylistTrackItemByAsin(final Context context, final String asin, final int source, final StorageLocation storageLocation) {
        Uri primeAdditionalTrackContentUri = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri(MediaProvider.CirrusBaseColumns.Source.convertToSourceId(source), "primeplaylist", asin);
        Item itemGenerate = new CustomStoragePrimePlaylistItemGenerator(context, "", getStorageLocationFileManager(), storageLocation).generate(primeAdditionalTrackContentUri, new PriorityInfo(DownloadPriority.USERINITIATED.name(), DownloadReason.USERINITIATED.name()), null);
        Log.verbose(TAG, "findPlaylistTrackItemByAsin: " + asin + ", source: " + source + ", trackUri: " + primeAdditionalTrackContentUri + ", item: " + itemGenerate + ", item destinationUri: " + itemGenerate.getDestinationUri());
        return itemGenerate;
    }

    boolean isPrimeTrackDownloaded(final String asin) {
        return getTrackDownloadHelper().isTrackDownloaded(asin);
    }

    Uri getMigratedTrackContentUri(final PreUnityTrack preUnityTrack) {
        Uri uriFindLocalTrackContentUriByLuid;
        if (preUnityTrack.isOwned() && preUnityTrack.asin != null) {
            String str = TAG;
            Log.verbose(str, "findLocalTrackContentUriByAsin (1)");
            uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackContentUriByAsin(this.context, preUnityTrack.asin);
            if (uriFindLocalTrackContentUriByLuid == null) {
                Log.verbose(str, "findLocalOwnedTrackContentUriByAsin");
                uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalOwnedTrackContentUriByAsin(this.context, preUnityTrack.asin);
            }
            if (uriFindLocalTrackContentUriByLuid == null) {
                SQLiteDatabase readOnlyDatabase = CirrusDatabase.getReadOnlyDatabase(this.context);
                Log.verbose(str, "findLocalTrackByTrackFileLocationUri");
                uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackByTrackFileLocationUri(readOnlyDatabase, preUnityTrack.localUri);
            }
        } else if (preUnityTrack.asin != null && (preUnityTrack.isPrime() || !preUnityTrack.isInLibrary())) {
            Log.verbose(TAG, "getUriForLocalPrimePlaylistTrack");
            uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackContentUriByAsin(this.context, preUnityTrack.asin);
            if (uriFindLocalTrackContentUriByLuid == null) {
                uriFindLocalTrackContentUriByLuid = MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus-local", "primeplaylist", preUnityTrack.asin);
            }
        } else if (preUnityTrack.asin != null && !preUnityTrack.isOwned() && preUnityTrack.isInLibrary()) {
            String str2 = TAG;
            Log.verbose(str2, "findLocalTrackContentUriByAsin");
            uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackContentUriByAsin(this.context, preUnityTrack.asin);
            if (uriFindLocalTrackContentUriByLuid == null) {
                Log.verbose(str2, "findLocalDownloadedCatalogTrackContentUriByAsin");
                uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalDownloadedCatalogTrackContentUriByAsin(this.context, preUnityTrack.asin);
            }
        } else if (preUnityTrack.isSideloaded()) {
            Log.verbose(TAG, "findLocalTrackByTrackFileLocationUri");
            uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackByTrackFileLocationUri(CirrusDatabase.getReadOnlyDatabase(this.context), preUnityTrack.localUri);
        } else if (preUnityTrack.isUploadedToCloud()) {
            Log.verbose(TAG, "findLocalTrackContentUriByLuid");
            uriFindLocalTrackContentUriByLuid = TrackUtil.findLocalTrackContentUriByLuid(this.context, preUnityTrack.luid);
        } else {
            Log.error(TAG, "Unknown track type: " + preUnityTrack);
            uriFindLocalTrackContentUriByLuid = null;
        }
        Log.verbose(TAG, "getMigratedTrackContentUri: " + uriFindLocalTrackContentUriByLuid + ", " + preUnityTrack);
        return uriFindLocalTrackContentUriByLuid;
    }

    private boolean moveTrackFile(final PreUnityTrack preUnityTrack, final Item item) throws Throwable {
        File musicRootDirectoryForFile;
        if (preUnityTrack.ownershipStatus == ContentOwnershipStatus.OWNED.getValue()) {
            Log.verbose(TAG, "Owned files are named properly. No need to rename or move them: " + preUnityTrack.localUri + ", item: " + item.getDestinationUri());
            return true;
        }
        if (preUnityTrack.localUri == null) {
            Log.warning(TAG, "The pre-Unity track localUri is null. Unable to migrate the track.");
            return false;
        }
        File file = new File(item.getDestinationUri());
        if (file.exists()) {
            Log.verbose(TAG, "The m4a file exists: " + file.getAbsolutePath());
            return true;
        }
        File file2 = new File(preUnityTrack.localUri);
        if (!file2.exists()) {
            throw new FileNotFoundException("The file is not found: " + file2.getAbsolutePath());
        }
        if (!file2.canRead()) {
            throw new FileNotFoundException("Unable to read: " + file2.getAbsolutePath());
        }
        File parentFile = file2.getParentFile();
        if (file2.getParentFile().equals(file.getParentFile())) {
            if (!FileUtil.moveFile(preUnityTrack.localUri, item.getDestinationUri())) {
                Log.error(TAG, "Unable to rename a track: " + preUnityTrack.localUri);
                return false;
            }
        } else {
            try {
                FileUtil.copyFile(file2, file, false);
                if (!file2.delete()) {
                    Log.warning(TAG, "Unable to delete: " + file2.getAbsolutePath());
                }
            } catch (IOException e) {
                Log.error(TAG, "Unable to copy the file: " + file2.getAbsolutePath() + " to " + file.getAbsolutePath());
                throw e;
            }
        }
        if (parentFile.exists() && (musicRootDirectoryForFile = FileUtil.getMusicRootDirectoryForFile(file2, getStorageLocationFileManager(), StorageLocationFileManager.Visibility.PRIVATE, this.context)) != null && musicRootDirectoryForFile.exists()) {
            FileUtil.deleteEmptyDirectoriesUntilRoot(parentFile, musicRootDirectoryForFile);
        }
        Log.verbose(TAG, "The track file is migrated: " + file2.getAbsolutePath() + " -> " + file.getAbsolutePath());
        return true;
    }

    private StorageLocation getStorageLocation(final PreUnityTrack preUnityTrack) {
        return getTrackDownloadHelper().getStorageLocationForLocalUri(preUnityTrack.isOwned(), preUnityTrack.localUri);
    }

    TrackDownloadHelper getTrackDownloadHelper() {
        if (this.mTrackDownloadHelper == null) {
            this.mTrackDownloadHelper = new TrackDownloadHelper(this.context, getStorageLocationFileManager());
        }
        return this.mTrackDownloadHelper;
    }

    private StorageLocationFileManager getStorageLocationFileManager() {
        return StorageLocationFileManagerProvider.getInstance(this.context).getFileManager();
    }

    Context getContext() {
        return this.context;
    }
}
