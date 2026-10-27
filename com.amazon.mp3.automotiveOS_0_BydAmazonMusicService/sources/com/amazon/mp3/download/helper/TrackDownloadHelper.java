package com.amazon.mp3.download.helper;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.library.scanner.MediaScanner;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.dialog.PurchasedStorageLocationChangeDialog;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.library.provider.source.cirrus.asinupdate.UpdateTracksAction;
import com.amazon.mp3.library.provider.source.cirrus.dbutils.LocalTrackUtil;
import com.amazon.mp3.library.service.sync.TrackSyncUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.service.streaming.StreamCache;
import com.amazon.mp3.service.metrics.BaseMetricsRecorder;
import com.amazon.mp3.service.metrics.ExternalStorageMetrics;
import com.amazon.mp3.util.DbUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DownloadState;
import com.amazon.music.downloads.Item;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;

/* JADX INFO: loaded from: classes3.dex */
public class TrackDownloadHelper {
    private static final String TAG = "TrackDownloadHelper";
    protected final Context mContext;
    private StorageLocationFileManager mFileManager;

    public TrackDownloadHelper(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public TrackDownloadHelper(Context context, StorageLocationFileManager storageLocationFileManager) {
        this.mContext = context.getApplicationContext();
        this.mFileManager = storageLocationFileManager;
    }

    public void updateDownloadState(String requestId, Item item, DownloadState state) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        switch (AnonymousClass2.$SwitchMap$com$amazon$music$downloads$DownloadState[state.ordinal()]) {
            case 1:
                updateTrackDownloadState(writableDatabase, 1, item);
                break;
            case 2:
            case 3:
            case 4:
                updateTrackDownloadState(writableDatabase, 3, item);
                break;
            case 5:
                if (item.getOriginalDownloadState() != null) {
                    updateTrackDownloadState(writableDatabase, item.getOriginalDownloadState().intValue(), item);
                } else {
                    updateTrackDownloadState(writableDatabase, 5, item);
                }
                break;
            case 6:
                updateTrackDownloadState(writableDatabase, 2, item);
                break;
            case 7:
                updateTrackDownloadState(writableDatabase, 4, item);
                break;
            case 8:
                onDownloaded(requestId, item);
                break;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.download.helper.TrackDownloadHelper$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$downloads$DownloadState;

        static {
            int[] iArr = new int[DownloadState.values().length];
            $SwitchMap$com$amazon$music$downloads$DownloadState = iArr;
            try {
                iArr[DownloadState.QUEUED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_PRIORITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_POLICY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.PAUSED_USER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.CANCELED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$music$downloads$DownloadState[DownloadState.DOWNLOADED.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    public boolean isTrackDownloaded(final String asin) {
        if (asin == null) {
            Log.error(TAG, "ASIN is null");
            return false;
        }
        int iIntFromCursor = DbUtil.intFromCursor(CirrusDatabase.getReadOnlyDatabase(this.mContext).query("Track", new String[]{"COUNT(*)"}, "asin =? AND download_state =? AND source =? ", new String[]{asin, Integer.toString(0), Integer.toString(1)}, null, null, null));
        Log.verbose(TAG, "Found " + iIntFromCursor + " of downloads for " + asin);
        return iIntFromCursor > 0;
    }

    private void onDownloaded(final String requestId, final Item item) {
        String existingLocalTrackLuidForAsin;
        String str = TAG;
        Log.debug(str, "Processing successful download for the track %s", requestId);
        String destinationUri = item.getDestinationUri();
        if (destinationUri.contains("file:///")) {
            destinationUri = Uri.parse(destinationUri).getPath();
        }
        reportDownloadedEvent(getStorageLocationForFile(item), item.isOwned() ? ExternalStorageMetrics.TrackType.OWNED : ExternalStorageMetrics.TrackType.PRIME);
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(this.mContext);
        if (item.isOwned()) {
            if (item.getAsin() != null && (existingLocalTrackLuidForAsin = TrackSyncUtil.getExistingLocalTrackLuidForAsin(writableDatabase, item.getAsin())) != null) {
                TrackSyncUtil.insertLocalTrackByLuid(writableDatabase, existingLocalTrackLuidForAsin, item.getDestinationUri());
            }
            MediaScanner.scan(this.mContext, destinationUri);
        }
        writableDatabase.beginTransaction();
        try {
            Context context = this.mContext;
            new UpdateTracksAction(context, MusicDownloader.getInstance(context), new CirrusSourceLibraryItemFactory(this.mContext)).deletePreviouslyDownloadedFile(item.getAsin());
            updateLocalTracksTable(writableDatabase, requestId, item);
            updateTrackDownloadState(writableDatabase, 0, item);
            saveTrack(writableDatabase, item.getLuid(), item.getAsin(), item.getDestinationUri());
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            Intent intent = new Intent();
            intent.setAction("com.amazon.mp3.action.sync_updated");
            this.mContext.sendBroadcast(intent);
            Log.debug(str, "Processed successful download for the track %s", requestId);
            new Thread() { // from class: com.amazon.mp3.download.helper.TrackDownloadHelper.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    if (item.isOwned()) {
                        return;
                    }
                    TrackDownloadHelper.this.loadArtworkForOfflinePrimeTrack(item.getAsin());
                }
            }.start();
            if (item.isOwned() && PurchasedStorageLocationChangeDialog.INSTANCE.shouldShowDialog(this.mContext)) {
                Intent intent2 = new Intent(this.mContext, (Class<?>) PurchasedStorageLocationChangeDialog.class);
                intent2.addFlags(268435456);
                this.mContext.startActivity(intent2);
            }
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            Intent intent3 = new Intent();
            intent3.setAction("com.amazon.mp3.action.sync_updated");
            this.mContext.sendBroadcast(intent3);
            Log.debug(TAG, "Processed successful download for the track %s", requestId);
            throw th;
        }
    }

    protected int updateTrackDownloadState(SQLiteDatabase db, int newState, Item item) {
        String[] strArr;
        String str;
        Log.debug(TAG, "Updating the Track %s, %s status to %s", item.getAsin(), item.getLuid(), Integer.valueOf(newState));
        ContentValues contentValues = new ContentValues();
        contentValues.put("download_state", Integer.valueOf(newState));
        if (item.isOwned()) {
            strArr = new String[]{String.valueOf(item.getLuid())};
            str = "luid = ?";
        } else {
            strArr = new String[]{String.valueOf(item.getAsin())};
            str = "asin = ?";
        }
        int iUpdate = db.update("Track", contentValues, str, strArr);
        this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI, null);
        this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
        return iUpdate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadArtworkForOfflinePrimeTrack(String asin) {
        if (asin == null) {
            return;
        }
        Log.debug(TAG, "Load artwork for offline prime track: %s", asin);
        CacheManager.getInstance().getOnCurrentThread(ImageLoaderFactory.ItemType.PRIME_PLAYLIST_TRACK, "cirrus-local", this.mContext.getResources().getDimensionPixelSize(R.dimen.now_playing_album_art_size), Long.toString(asin.hashCode()), true);
    }

    private StorageLocation getStorageLocationForFile(Item item) {
        return getStorageLocationForLocalUri(item.isOwned(), item.getDestinationUri());
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0045 A[Catch: Exception -> 0x0081, TryCatch #0 {Exception -> 0x0081, blocks: (B:3:0x0002, B:5:0x0007, B:7:0x002e, B:10:0x0049, B:14:0x0052, B:13:0x0050, B:9:0x0045, B:4:0x0005), top: B:18:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.music.externalstorage.StorageLocation getStorageLocationForLocalUri(boolean r7, java.lang.String r8) {
        /*
            r6 = this;
            if (r7 == 0) goto L5
            com.amazon.music.externalstorage.StorageLocationFileManager$Visibility r7 = com.amazon.music.externalstorage.StorageLocationFileManager.Visibility.PUBLIC     // Catch: java.lang.Exception -> L81
            goto L7
        L5:
            com.amazon.music.externalstorage.StorageLocationFileManager$Visibility r7 = com.amazon.music.externalstorage.StorageLocationFileManager.Visibility.PRIVATE     // Catch: java.lang.Exception -> L81
        L7:
            com.amazon.music.externalstorage.StorageLocationFileManager r0 = r6.getFileManager()     // Catch: java.lang.Exception -> L81
            com.amazon.music.externalstorage.StorageLocation r1 = com.amazon.music.externalstorage.StorageLocation.SDCARD     // Catch: java.lang.Exception -> L81
            java.io.File r0 = r0.getStorageDirectory(r1, r7)     // Catch: java.lang.Exception -> L81
            java.lang.String r2 = r0.getPath()     // Catch: java.lang.Exception -> L81
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L81
            r3.<init>()     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = java.io.File.separator     // Catch: java.lang.Exception -> L81
            r3.append(r4)     // Catch: java.lang.Exception -> L81
            java.lang.String r5 = "Private"
            r3.append(r5)     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L81
            boolean r3 = r2.endsWith(r3)     // Catch: java.lang.Exception -> L81
            if (r3 != 0) goto L45
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L81
            r3.<init>()     // Catch: java.lang.Exception -> L81
            r3.append(r4)     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = "Public"
            r3.append(r4)     // Catch: java.lang.Exception -> L81
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Exception -> L81
            boolean r3 = r2.endsWith(r3)     // Catch: java.lang.Exception -> L81
            if (r3 == 0) goto L49
        L45:
            java.lang.String r2 = r0.getParent()     // Catch: java.lang.Exception -> L81
        L49:
            boolean r0 = r8.startsWith(r2)     // Catch: java.lang.Exception -> L81
            if (r0 == 0) goto L50
            goto L52
        L50:
            com.amazon.music.externalstorage.StorageLocation r1 = com.amazon.music.externalstorage.StorageLocation.DEVICE     // Catch: java.lang.Exception -> L81
        L52:
            java.lang.String r0 = com.amazon.mp3.download.helper.TrackDownloadHelper.TAG     // Catch: java.lang.Exception -> L81
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L81
            r3.<init>()     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = "getStorageLocationForLocalUri: "
            r3.append(r4)     // Catch: java.lang.Exception -> L81
            r3.append(r1)     // Catch: java.lang.Exception -> L81
            java.lang.String r4 = ", storage: "
            r3.append(r4)     // Catch: java.lang.Exception -> L81
            r3.append(r2)     // Catch: java.lang.Exception -> L81
            java.lang.String r2 = ", visibility: "
            r3.append(r2)     // Catch: java.lang.Exception -> L81
            r3.append(r7)     // Catch: java.lang.Exception -> L81
            java.lang.String r7 = ", localFileUri: "
            r3.append(r7)     // Catch: java.lang.Exception -> L81
            r3.append(r8)     // Catch: java.lang.Exception -> L81
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Exception -> L81
            com.amazon.mp3.util.Log.verbose(r0, r7)     // Catch: java.lang.Exception -> L81
            goto L83
        L81:
            com.amazon.music.externalstorage.StorageLocation r1 = com.amazon.music.externalstorage.StorageLocation.DEVICE
        L83:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.helper.TrackDownloadHelper.getStorageLocationForLocalUri(boolean, java.lang.String):com.amazon.music.externalstorage.StorageLocation");
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0045 A[Catch: ExternalStorageNotSupportedException | FileNotFoundException | NullPointerException -> 0x004e, TryCatch #0 {ExternalStorageNotSupportedException | FileNotFoundException | NullPointerException -> 0x004e, blocks: (B:3:0x0002, B:5:0x0007, B:7:0x002e, B:10:0x0049, B:9:0x0045, B:4:0x0005), top: B:14:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isInInternalStorageLocation(final boolean r5, final java.lang.String r6) {
        /*
            r4 = this;
            if (r5 == 0) goto L5
            com.amazon.music.externalstorage.StorageLocationFileManager$Visibility r5 = com.amazon.music.externalstorage.StorageLocationFileManager.Visibility.PUBLIC     // Catch: java.lang.Throwable -> L4e
            goto L7
        L5:
            com.amazon.music.externalstorage.StorageLocationFileManager$Visibility r5 = com.amazon.music.externalstorage.StorageLocationFileManager.Visibility.PRIVATE     // Catch: java.lang.Throwable -> L4e
        L7:
            com.amazon.music.externalstorage.StorageLocationFileManager r0 = r4.getFileManager()     // Catch: java.lang.Throwable -> L4e
            com.amazon.music.externalstorage.StorageLocation r1 = com.amazon.music.externalstorage.StorageLocation.DEVICE     // Catch: java.lang.Throwable -> L4e
            java.io.File r5 = r0.getStorageDirectory(r1, r5)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r0 = r5.getPath()     // Catch: java.lang.Throwable -> L4e
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r1.<init>()     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = java.io.File.separator     // Catch: java.lang.Throwable -> L4e
            r1.append(r2)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r3 = "Private"
            r1.append(r3)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L4e
            boolean r1 = r0.endsWith(r1)     // Catch: java.lang.Throwable -> L4e
            if (r1 != 0) goto L45
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4e
            r1.<init>()     // Catch: java.lang.Throwable -> L4e
            r1.append(r2)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r2 = "Public"
            r1.append(r2)     // Catch: java.lang.Throwable -> L4e
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L4e
            boolean r1 = r0.endsWith(r1)     // Catch: java.lang.Throwable -> L4e
            if (r1 == 0) goto L49
        L45:
            java.lang.String r0 = r5.getParent()     // Catch: java.lang.Throwable -> L4e
        L49:
            boolean r5 = r6.startsWith(r0)     // Catch: java.lang.Throwable -> L4e
            return r5
        L4e:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.download.helper.TrackDownloadHelper.isInInternalStorageLocation(boolean, java.lang.String):boolean");
    }

    private void reportDownloadedEvent(StorageLocation storageLocation, ExternalStorageMetrics.TrackType trackType) {
        if (storageLocation == StorageLocation.DEVICE) {
            BaseMetricsRecorder.getMetricsRecorder(this.mContext.getApplicationContext()).getExternalStorageMetrics().recordDeviceDownload(trackType);
        } else {
            BaseMetricsRecorder.getMetricsRecorder(this.mContext.getApplicationContext()).getExternalStorageMetrics().recordSDCardDownload(trackType);
        }
    }

    protected void updateLocalTracksTable(SQLiteDatabase db, String requestId, Item item) {
        MediaItemId mediaItemId;
        String destinationUri = item.getDestinationUri();
        String luid = item.getLuid();
        String asin = item.getAsin();
        LocalTrackUtil localTrackUtil = new LocalTrackUtil(db);
        String str = TAG;
        Log.debug(str, "Storing the following in the local tracks table %s, %s", requestId, destinationUri);
        if (localTrackUtil.insertTrackLocalUri(this.mContext, luid, asin, destinationUri) < 0) {
            Log.warning(str, "Database insert failed for: %s, %s", requestId, destinationUri);
        } else {
            Log.debug(str, "Stored the local path in the local tracks table");
        }
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        String id = null;
        if (currentMediaItem != null && (mediaItemId = currentMediaItem.getMediaItemId(MediaItemId.Type.LUID)) != null) {
            id = mediaItemId.getId();
        }
        if (luid == null || luid.equals(id)) {
            return;
        }
        Log.debug(str, "Removing track with luid: %s from cache", luid);
        StreamCache.getInstance(this.mContext).clearTrack(luid);
    }

    protected void saveTrack(SQLiteDatabase db, String luid, String asin, String localUri) {
        if (asin == null && luid == null) {
            Log.error(TAG, "Could not save track to database");
            return;
        }
        if (!TextUtils.isEmpty(asin)) {
            TrackSyncUtil.insertLocalTrackByAsin(db, asin, localUri);
        } else {
            TrackSyncUtil.insertLocalTrackByLuid(db, luid, localUri);
        }
        this.mContext.getContentResolver().notifyChange(CirrusMediaSource.NOTIFICATION_URI_LOCAL, null);
    }

    protected StorageLocationFileManager getFileManager() {
        if (this.mFileManager == null) {
            this.mFileManager = StorageLocationFileManager.create(this.mContext, Environment.DIRECTORY_MUSIC);
        }
        return this.mFileManager;
    }
}
