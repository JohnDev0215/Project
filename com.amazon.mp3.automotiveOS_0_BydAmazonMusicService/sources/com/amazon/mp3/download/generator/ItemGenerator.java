package com.amazon.mp3.download.generator;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.amazon.digitalmusicplayback.DownloadPriority;
import com.amazon.digitalmusicplayback.DownloadReason;
import com.amazon.mp3.download.ImageCacheUri;
import com.amazon.mp3.library.cache.image.loader.ImageLoaderFactory;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.provider.source.cirrus.CirrusMediaSource;
import com.amazon.mp3.prime.ContentCatalogStatus;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PrimeContentUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.downloads.Item;
import com.amazon.music.downloads.PriorityInfo;
import com.amazon.music.downloads.notification.NotificationInfo;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.subscription.UserSubscription;
import java.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public class ItemGenerator {
    private static final String TAG = "ItemGenerator";
    protected final Context mContext;
    protected final StorageLocationFileManager mStorageLocationFileManager;
    public static final String[] PROJECTION = {"luid", "asin", "title", "album", "album_asin", "artist", "album_artist", "disc_num", "track_num", "download_state", "prime_status", "purchased", "uploaded", "ownership_status", "extension", "is_explicit", "content_encoding", "is_exclusion_tagged"};
    private static boolean sIsDeviceAuthDeclined = false;

    public ItemGenerator(Context context, StorageLocationFileManager storageLocationFileManager) {
        this.mContext = context.getApplicationContext();
        this.mStorageLocationFileManager = storageLocationFileManager;
    }

    public Item generate(Uri trackUri, boolean downloadNonAddedPrime, final PriorityInfo priorityInfo, final Integer originalDownloadState) {
        if (trackUri == null) {
            return null;
        }
        if (CirrusMediaSource.matchLocal(trackUri)) {
            Log.warning(TAG, "The ItemGenerator is called for a local track: " + trackUri);
        }
        Cursor cursorQuery = this.mContext.getContentResolver().query(trackUri, PROJECTION, null, null, null);
        if (cursorQuery == null) {
            Log.error(TAG, "cursor returned null");
            return null;
        }
        if (cursorQuery.moveToFirst()) {
            String string = cursorQuery.getString(cursorQuery.getColumnIndex("luid"));
            String string2 = cursorQuery.getString(cursorQuery.getColumnIndex("asin"));
            String string3 = cursorQuery.getString(cursorQuery.getColumnIndex("title"));
            String string4 = cursorQuery.getString(cursorQuery.getColumnIndex("extension"));
            String string5 = cursorQuery.getString(cursorQuery.getColumnIndex("artist"));
            String string6 = cursorQuery.getString(cursorQuery.getColumnIndex("album_artist"));
            String string7 = cursorQuery.getString(cursorQuery.getColumnIndex("album"));
            String string8 = cursorQuery.getString(cursorQuery.getColumnIndex("album_asin"));
            String string9 = cursorQuery.getString(cursorQuery.getColumnIndex("disc_num"));
            String string10 = cursorQuery.getString(cursorQuery.getColumnIndex("track_num"));
            ContentCatalogStatus contentCatalogStatusFromValue = ContentCatalogStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("prime_status")));
            ContentOwnershipStatus contentOwnershipStatusFromValue = ContentOwnershipStatus.fromValue(cursorQuery.getInt(cursorQuery.getColumnIndex("ownership_status")));
            int columnIndex = cursorQuery.getColumnIndex("purchased");
            int i = cursorQuery.isNull(columnIndex) ? 0 : cursorQuery.getInt(columnIndex);
            int columnIndex2 = cursorQuery.getColumnIndex("uploaded");
            int i2 = cursorQuery.isNull(columnIndex2) ? 0 : cursorQuery.getInt(columnIndex2);
            cursorQuery.close();
            return generate(string2, string, string3, string7, string8, string6, string5, string9, string10, string4, contentCatalogStatusFromValue, contentOwnershipStatusFromValue, downloadNonAddedPrime, i == 1 || i2 == 1 || contentOwnershipStatusFromValue == ContentOwnershipStatus.OWNED, priorityInfo, originalDownloadState);
        }
        Log.error(TAG, "cursor had size zero");
        return null;
    }

    public Item generate(String asin, String luid, String title, String album, String albumAsin, String albumArtist, String artist, String discNum, String trackNum, String extension, ContentCatalogStatus catalogStatus, ContentOwnershipStatus ownershipStatus, boolean downloadNonAddedCatalogTracks, boolean isOwned, final PriorityInfo priorityInfo, final Integer originalDownloadState) {
        boolean z;
        boolean zIsTrackDownloadableCatalogContent = PrimeContentUtil.isTrackDownloadableCatalogContent(catalogStatus, ownershipStatus);
        if (luid != null && IdGenerator.isPrimeLuid(luid)) {
            Log.debug(TAG, "Keeping temporary prime luid: " + luid + " to be updated by the library sync.");
        }
        if (luid != null && LocalLuidGenerator.isLocalLuid(luid)) {
            Log.warning(TAG, "The ItemGenerator is called for a local track: " + luid);
            return null;
        }
        if (zIsTrackDownloadableCatalogContent && isOwned) {
            String str = TAG;
            Log.warning(str, "Owned track is incorrectly tagged as a Prime or Hawkfire download");
            Log.debug(str, "Track with luid: %s and asin: %s is not a Prime or Hawkfire track", luid, asin);
            z = false;
        } else {
            z = zIsTrackDownloadableCatalogContent;
        }
        if (PrimeContentUtil.isCatalogNonAdded(catalogStatus, ownershipStatus) && !downloadNonAddedCatalogTracks) {
            Log.info(TAG, "not downloading a non-added catalog track");
            return null;
        }
        if (!z && TextUtils.isEmpty(luid)) {
            Log.warning(TAG, "Cannot download with empty luid");
            return null;
        }
        if (z) {
            if (TextUtils.isEmpty(asin)) {
                Log.warning(TAG, "Cannot download with empty asin");
                return null;
            }
            if (catalogStatus.isPreviouslyCatalog()) {
                Log.warning(TAG, "Cannot download expired content");
                return null;
            }
        }
        DeviceAuthorizationManager deviceAuthorizationManager = DeviceAuthorizationManager.getInstance();
        if (!deviceAuthorizationManager.isAuthorized() && !sIsDeviceAuthDeclined && !deviceAuthorizationManager.authorizeDevice()) {
            sIsDeviceAuthDeclined = true;
            Log.warning(TAG, "device not authorized");
            return null;
        }
        sIsDeviceAuthDeclined = false;
        if (z) {
            if (!deviceAuthorizationManager.isPrimeAuthorized() || deviceAuthorizationManager.shouldRefreshPrimeAuthorization()) {
                DownloadAvailabilityStateListener downloadAvailabilityStateListener = new DownloadAvailabilityStateListener(this.mContext);
                PrimeContentUtil.checkOfflineAccess(getCatalogContent(catalogStatus), downloadAvailabilityStateListener);
                synchronized (downloadAvailabilityStateListener) {
                    while (downloadAvailabilityStateListener.shouldWait()) {
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
        }
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        if (z && catalogStatus.getValue() == ContentCatalogStatus.HAWKFIRE.getValue() && !userSubscription.isHawkfireAllDevices()) {
            Log.warning(TAG, "cannot download hawkfire content. ASIN:" + asin + "  ownershipStatus: " + ownershipStatus.getValue() + "  catalogStatus: " + catalogStatus.getValue());
            return null;
        }
        NotificationInfo notificationInfo = new NotificationInfo(title, album, ImageCacheUri.generateUri(ImageLoaderFactory.ItemType.ALBUM.intValue(), IdGenerator.generateAlbumId(albumAsin, album)).toString());
        String harleyTrackDestinationUri = getHarleyTrackDestinationUri(title, asin, trackNum, luid, extension, isOwned);
        String trackDestinationDirectory = getTrackDestinationDirectory(isOwned);
        Item.Builder builder = new Item.Builder();
        builder.addAsin(asin);
        builder.addLuid(luid);
        builder.setIsOwned(!z);
        builder.addDestinationUri(harleyTrackDestinationUri);
        builder.addDestinationDirectory(trackDestinationDirectory);
        builder.addNotificationInfo(notificationInfo);
        builder.setOriginalDownloadState(originalDownloadState);
        if (originalDownloadState != null && (originalDownloadState.intValue() == 6 || originalDownloadState.intValue() == 7)) {
            builder.addPriorityInfo(new PriorityInfo(DownloadPriority.BACKFILL.name(), DownloadReason.NEWDRMDOWNLOAD.name()));
        } else {
            builder.addPriorityInfo(priorityInfo);
        }
        return builder.build();
    }

    protected String getHarleyTrackDestinationUri(final String title, final String asin, final String trackNum, final String luid, final String extension, final boolean isOwned) {
        String strGenerateHarleyPath = FileNameGenerator.generateHarleyPath(trackNum, asin, title, luid, extension, isOwned, this.mStorageLocationFileManager);
        Objects.requireNonNull(strGenerateHarleyPath);
        return strGenerateHarleyPath;
    }

    protected String getTrackDestinationDirectory(final boolean isOwned) {
        String strGenerateDirectoryPath = FileNameGenerator.generateDirectoryPath(this.mStorageLocationFileManager);
        if (!isOwned) {
            return strGenerateDirectoryPath;
        }
        return strGenerateDirectoryPath + FileNameGenerator.DIR_SEPARATOR + "Purchased";
    }

    static CatalogContent getCatalogContent(final ContentCatalogStatus catalogStatus) {
        return new CatalogContent() { // from class: com.amazon.mp3.download.generator.ItemGenerator.1
            @Override // com.amazon.mp3.library.item.CatalogContent
            public String getSource() {
                return "cirrus";
            }

            @Override // com.amazon.mp3.library.item.CatalogContent
            public boolean isAllOwned() {
                return false;
            }

            @Override // com.amazon.mp3.library.item.CatalogContent
            public boolean isAllPreviouslyCatalog() {
                return false;
            }

            @Override // com.amazon.mp3.library.item.CatalogContent
            public boolean isExplicit() {
                return false;
            }

            @Override // com.amazon.mp3.library.item.CatalogContent
            public boolean isAnyCatalog() {
                return catalogStatus.isHawkfire();
            }

            @Override // com.amazon.mp3.library.item.CatalogContent
            public ContentCatalogStatus getMaxNonPreviousContentCatalogStatus() {
                return catalogStatus;
            }
        };
    }
}
