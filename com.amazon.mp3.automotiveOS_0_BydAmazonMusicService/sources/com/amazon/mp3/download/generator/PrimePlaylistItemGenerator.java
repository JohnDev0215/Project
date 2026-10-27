package com.amazon.mp3.download.generator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.StorageLocationFileManager;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistItemGenerator {
    public static final String[] PROJECTION = {"asin", "title", "album", "artist", "album_artist", "track_num", "download_state", "catalog_status"};
    private static final String TAG = "PrimePlaylistItemGenerator";
    private static boolean sIsDeviceAuthDeclined;
    protected final Context mContext;
    private final String mPlaylistName;
    protected final StorageLocationFileManager mStorageLocationFileManager;

    public PrimePlaylistItemGenerator(Context context, String playlistName, StorageLocationFileManager storageLocationFileManager) {
        this.mContext = context.getApplicationContext();
        this.mPlaylistName = playlistName;
        this.mStorageLocationFileManager = storageLocationFileManager;
    }

    public Item generate(Uri trackUri, final PriorityInfo priorityInfo, final Integer originalDownloadState) {
        String primeAdditionalTrackAsin = MediaProvider.Tracks.getPrimeAdditionalTrackAsin(trackUri);
        Cursor cursorQuery = CirrusDatabase.getWritableDatabase(this.mContext.getApplicationContext()).query("PrimePlaylistTracks", PROJECTION, "asin = ?", new String[]{primeAdditionalTrackAsin}, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("artist"));
            String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("album_artist"));
            String string4 = cursorQuery.getString(cursorQuery.getColumnIndex("album"));
            String string5 = cursorQuery.getString(cursorQuery.getColumnIndex("track_num"));
            ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("catalog_status")));
            cursorQuery.close();
            if (!contentCatalogStatusFromValue.isPreviouslyCatalog()) {
                return generate(primeAdditionalTrackAsin, string, string4, string3, string2, string5, contentCatalogStatusFromValue, priorityInfo, originalDownloadState);
            }
        }
        return null;
    }

    public Item generate(String asin, String title, String album, String albumArtist, String artist, String trackNum, ContentCatalogStatus catalogStatus, final PriorityInfo priorityInfo, final Integer originalDownloadState) {
        NotificationInfo notificationInfo = new NotificationInfo(title, this.mPlaylistName, null);
        String trackDestinationUri = getTrackDestinationUri(asin, title, album, albumArtist, artist, trackNum);
        String trackDestinationDirectory = getTrackDestinationDirectory();
        DeviceAuthorizationManager deviceAuthorizationManager = DeviceAuthorizationManager.getInstance();
        if (!deviceAuthorizationManager.isAuthorized() && !sIsDeviceAuthDeclined && !deviceAuthorizationManager.authorizeDevice()) {
            sIsDeviceAuthDeclined = true;
            Log.warning(TAG, "device not authorized");
            return null;
        }
        sIsDeviceAuthDeclined = false;
        if (!deviceAuthorizationManager.isPrimeAuthorized() || deviceAuthorizationManager.shouldRefreshPrimeAuthorization()) {
            DownloadAvailabilityStateListener downloadAvailabilityStateListener = new DownloadAvailabilityStateListener(this.mContext);
            PrimeContentUtil.checkOfflineAccess(ItemGenerator.getCatalogContent(catalogStatus), downloadAvailabilityStateListener);
            while (downloadAvailabilityStateListener.shouldWait()) {
                synchronized (downloadAvailabilityStateListener) {
                    try {
                        downloadAvailabilityStateListener.wait();
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
        if (!deviceAuthorizationManager.isPrimeAuthorized()) {
            Log.warning(TAG, "cannot download prime content");
            return null;
        }
        Item.Builder builder = new Item.Builder();
        builder.addAsin(asin);
        builder.addLuid(null);
        builder.setIsOwned(false);
        builder.addDestinationUri(trackDestinationUri);
        builder.addDestinationDirectory(trackDestinationDirectory);
        builder.addNotificationInfo(notificationInfo);
        builder.setOriginalDownloadState(originalDownloadState);
        builder.addPriorityInfo(priorityInfo);
        return builder.build();
    }

    protected String getTrackDestinationDirectory() {
        return FileNameGenerator.generateDirectoryPath(this.mStorageLocationFileManager);
    }

    protected String getTrackDestinationUri(final String asin, final String title, final String album, final String albumArtist, final String artist, final String trackNum) {
        return FileNameGenerator.generatePath(this.mContext, album, artist, albumArtist, asin, title, (String) null, trackNum, (String) null, true, true, this.mStorageLocationFileManager);
    }
}
