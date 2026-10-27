package com.amazon.mp3.playback.harley;

import android.os.Environment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.download.DownloadQualityPreferences;
import com.amazon.mp3.download.MusicDownloader;
import com.amazon.mp3.download.generator.FileNameGenerator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.util.StorageInfo;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.externalstorage.ExternalStorageNotSupportedException;
import com.amazon.music.externalstorage.StorageLocation;
import com.amazon.music.externalstorage.StorageLocationFileManager;
import com.amazon.music.externalstorage.StorageLocationPreference;
import java.io.File;
import java.io.FileNotFoundException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HarleyDownloadProperties.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001d\n\u0002\u0010\b\n\u0002\b\u001a\b\u0016\u0018\u0000 K2\u00020\u0001:\u0001KB'\b\u0007\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010H\u001a\u000202\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0014¢\u0006\u0004\bI\u0010JJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\t\u001a\u00020\bH\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\nH\u0002J\b\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u000e\u001a\u00020\nH\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0015\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u0019R\"\u0010\u001a\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010 \u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\"\u0010&\u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b&\u0010!\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R\u0014\u0010)\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010!R\"\u0010*\u001a\u00020\u00068\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b*\u0010!\u001a\u0004\b+\u0010#\"\u0004\b,\u0010%R\"\u0010-\u001a\u00020\u00048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b-\u0010\u0019\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\"\u00103\u001a\u0002028\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\"\u00109\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b9\u0010\u001b\u001a\u0004\b:\u0010\u001d\"\u0004\b;\u0010\u001fR\"\u0010<\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b<\u0010\u001b\u001a\u0004\b=\u0010\u001d\"\u0004\b>\u0010\u001fR\"\u0010?\u001a\u00020\n8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b?\u0010\u001b\u001a\u0004\b@\u0010\u001d\"\u0004\bA\u0010\u001fR\"\u0010B\u001a\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bB\u0010C\u001a\u0004\bD\u0010E\"\u0004\bF\u0010G¨\u0006L"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyDownloadProperties;", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "storageLocationFileManager", "", "isSdCardAvailable", "", "getPurchasedDownloadDirectory", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "getUserApplicableDownloadQualitySelected", "", "getMinFreeSpaceToLeave", "getMaxCacheSize", "getMaxStorageSize", "getTotalSpaceMB", "Lcom/amazon/music/externalstorage/StorageLocation;", "getPreferredStorageLocation", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "getStorageLocationFileManager", "()Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "Lcom/amazon/mp3/util/StorageInfo;", "storageInfo", "Lcom/amazon/mp3/util/StorageInfo;", "getStorageInfo", "()Lcom/amazon/mp3/util/StorageInfo;", "Z", "downloadProgressNotificationInterval", "J", "getDownloadProgressNotificationInterval", "()J", "setDownloadProgressNotificationInterval", "(J)V", "purchasedContentDownloadDirectory", "Ljava/lang/String;", "getPurchasedContentDownloadDirectory", "()Ljava/lang/String;", "setPurchasedContentDownloadDirectory", "(Ljava/lang/String;)V", "catalogContentDownloadDirectory", "getCatalogContentDownloadDirectory", "setCatalogContentDownloadDirectory", "additionalPath", "additionalDownloadStoragePathForCatalog", "getAdditionalDownloadStoragePathForCatalog", "setAdditionalDownloadStoragePathForCatalog", "shouldStorePurchasedContentWithFriendlyName", "getShouldStorePurchasedContentWithFriendlyName", "()Z", "setShouldStorePurchasedContentWithFriendlyName", "(Z)V", "", "maxNumConcurrentDownload", "I", "getMaxNumConcurrentDownload", "()I", "setMaxNumConcurrentDownload", "(I)V", "minimumFreeSpace", "getMinimumFreeSpace", "setMinimumFreeSpace", "maximumStorageSize", "getMaximumStorageSize", "setMaximumStorageSize", "maximumCacheSize", "getMaximumCacheSize", "setMaximumCacheSize", "downloadQualityPreference", "Lcom/amazon/mp3/download/DownloadQualityPreferences;", "getDownloadQualityPreference", "()Lcom/amazon/mp3/download/DownloadQualityPreferences;", "setDownloadQualityPreference", "(Lcom/amazon/mp3/download/DownloadQualityPreferences;)V", "numAvailableProcessor", "<init>", "(Lcom/amazon/music/externalstorage/StorageLocationFileManager;ILcom/amazon/mp3/util/StorageInfo;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class HarleyDownloadProperties extends DownloadProperties {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private String additionalDownloadStoragePathForCatalog;
    private final String additionalPath;
    private String catalogContentDownloadDirectory;
    private long downloadProgressNotificationInterval;
    private DownloadQualityPreferences downloadQualityPreference;
    private boolean isSdCardAvailable;
    private int maxNumConcurrentDownload;
    private long maximumCacheSize;
    private long maximumStorageSize;
    private long minimumFreeSpace;
    private String purchasedContentDownloadDirectory;
    private boolean shouldStorePurchasedContentWithFriendlyName;
    private final StorageInfo storageInfo;
    private final StorageLocationFileManager storageLocationFileManager;

    @JvmOverloads
    public HarleyDownloadProperties() {
        this(null, 0, null, 7, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ HarleyDownloadProperties(StorageLocationFileManager storageLocationFileManager, int i, StorageInfo storageInfo, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 1) != 0) {
            storageLocationFileManager = StorageLocationFileManager.create(AmazonApplication.getContext(), Environment.DIRECTORY_MUSIC);
            Intrinsics.checkNotNullExpressionValue(storageLocationFileManager, "create(AmazonApplication…ironment.DIRECTORY_MUSIC)");
        }
        i = (i2 & 2) != 0 ? Runtime.getRuntime().availableProcessors() : i;
        if ((i2 & 4) != 0) {
            storageInfo = StorageInfo.getInstance();
            Intrinsics.checkNotNullExpressionValue(storageInfo, "getInstance()");
        }
        this(storageLocationFileManager, i, storageInfo);
    }

    public final StorageLocationFileManager getStorageLocationFileManager() {
        return this.storageLocationFileManager;
    }

    /* JADX INFO: compiled from: HarleyDownloadProperties.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\bJ\u001a\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/playback/harley/HarleyDownloadProperties$Companion;", "", "()V", "MIN_FREE_SPACE_TO_LEAVE_MB", "", "TAG", "", "checkIsSdCardAvailable", "", "getAdditionalDownloadStoragePathForCatalog", "storageLocationFileManager", "Lcom/amazon/music/externalstorage/StorageLocationFileManager;", "isSdCardAvailable", "getCatalogDownloadDirectory", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {

        /* JADX INFO: compiled from: HarleyDownloadProperties.kt */
        @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[StorageLocation.values().length];
                iArr[StorageLocation.DEVICE.ordinal()] = 1;
                iArr[StorageLocation.SDCARD.ordinal()] = 2;
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ String getCatalogDownloadDirectory$default(Companion companion, StorageLocationFileManager storageLocationFileManager, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                storageLocationFileManager = StorageLocationFileManager.create(AmazonApplication.getContext(), Environment.DIRECTORY_MUSIC);
                Intrinsics.checkNotNullExpressionValue(storageLocationFileManager, "create(AmazonApplication…ironment.DIRECTORY_MUSIC)");
            }
            if ((i & 2) != 0) {
                z = companion.checkIsSdCardAvailable();
            }
            return companion.getCatalogDownloadDirectory(storageLocationFileManager, z);
        }

        public final String getCatalogDownloadDirectory(StorageLocationFileManager storageLocationFileManager, boolean isSdCardAvailable) throws ExternalStorageNotSupportedException, FileNotFoundException {
            File storageDirectory;
            Intrinsics.checkNotNullParameter(storageLocationFileManager, "storageLocationFileManager");
            boolean z = isSdCardAvailable || PlatformUtil.isVersionOrLower(22);
            if (z) {
                storageDirectory = storageLocationFileManager.getPreferredStorageDirectory(StorageLocationFileManager.Visibility.PRIVATE);
            } else if (!z) {
                storageDirectory = storageLocationFileManager.getStorageDirectory(StorageLocation.DEVICE, StorageLocationFileManager.Visibility.PRIVATE);
            } else {
                throw new NoWhenBranchMatchedException();
            }
            if (storageDirectory == null) {
                return "";
            }
            String absolutePath = storageDirectory.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "catalogDownloadDirectory.absolutePath");
            return absolutePath;
        }

        public static /* synthetic */ String getAdditionalDownloadStoragePathForCatalog$default(Companion companion, StorageLocationFileManager storageLocationFileManager, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                storageLocationFileManager = StorageLocationFileManager.create(AmazonApplication.getContext(), Environment.DIRECTORY_MUSIC);
                Intrinsics.checkNotNullExpressionValue(storageLocationFileManager, "create(AmazonApplication…ironment.DIRECTORY_MUSIC)");
            }
            if ((i & 2) != 0) {
                z = companion.checkIsSdCardAvailable();
            }
            return companion.getAdditionalDownloadStoragePathForCatalog(storageLocationFileManager, z);
        }

        public final String getAdditionalDownloadStoragePathForCatalog(StorageLocationFileManager storageLocationFileManager, boolean isSdCardAvailable) {
            Intrinsics.checkNotNullParameter(storageLocationFileManager, "storageLocationFileManager");
            if (!isSdCardAvailable) {
                return "";
            }
            try {
                StorageLocation storageLocation = StorageLocation.SDCARD;
                StorageLocationFileManager.Visibility visibility = StorageLocationFileManager.Visibility.PRIVATE;
                File storageDirectory = storageLocationFileManager.getStorageDirectory(storageLocation, visibility);
                if (storageDirectory == null) {
                    return "";
                }
                StorageLocation preferredStorageLocation = new StorageLocationPreference(AmazonApplication.getContext()).getPreferredStorageLocation();
                int i = preferredStorageLocation == null ? -1 : WhenMappings.$EnumSwitchMapping$0[preferredStorageLocation.ordinal()];
                if (i == 1) {
                    String absolutePath = storageDirectory.getAbsolutePath();
                    Intrinsics.checkNotNullExpressionValue(absolutePath, "sdCardDirectory.absolutePath");
                    return absolutePath;
                }
                if (i != 2) {
                    throw new NoWhenBranchMatchedException();
                }
                String absolutePath2 = storageLocationFileManager.getStorageDirectory(StorageLocation.DEVICE, visibility).getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath2, "storageLocationFileManag…ity.PRIVATE).absolutePath");
                return absolutePath2;
            } catch (ExternalStorageNotSupportedException e) {
                Log.error(HarleyDownloadProperties.TAG, e.getMessage());
                return "";
            } catch (FileNotFoundException e2) {
                Log.error(HarleyDownloadProperties.TAG, e2.getMessage());
                return "";
            } catch (IllegalStateException e3) {
                Log.error(HarleyDownloadProperties.TAG, e3.getMessage());
                return "";
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean checkIsSdCardAvailable() {
            boolean zSecondaryStoragePresent = AmazonApplication.getCapabilities().secondaryStoragePresent(AmazonApplication.getContext());
            Log.debug(HarleyDownloadProperties.TAG, Intrinsics.stringPlus("Sd card availability: ", Boolean.valueOf(zSecondaryStoragePresent)));
            return zSecondaryStoragePresent;
        }
    }

    @JvmOverloads
    public HarleyDownloadProperties(StorageLocationFileManager storageLocationFileManager, int i, StorageInfo storageInfo) {
        Intrinsics.checkNotNullParameter(storageLocationFileManager, "storageLocationFileManager");
        Intrinsics.checkNotNullParameter(storageInfo, "storageInfo");
        this.storageLocationFileManager = storageLocationFileManager;
        this.storageInfo = storageInfo;
        Companion companion = INSTANCE;
        boolean zCheckIsSdCardAvailable = companion.checkIsSdCardAvailable();
        this.isSdCardAvailable = zCheckIsSdCardAvailable;
        this.downloadProgressNotificationInterval = 500L;
        this.purchasedContentDownloadDirectory = getPurchasedDownloadDirectory(storageLocationFileManager, zCheckIsSdCardAvailable);
        this.catalogContentDownloadDirectory = companion.getCatalogDownloadDirectory(storageLocationFileManager, this.isSdCardAvailable);
        String additionalDownloadStoragePathForCatalog = companion.getAdditionalDownloadStoragePathForCatalog(storageLocationFileManager, this.isSdCardAvailable);
        this.additionalPath = additionalDownloadStoragePathForCatalog;
        this.additionalDownloadStoragePathForCatalog = Intrinsics.areEqual(additionalDownloadStoragePathForCatalog, getCatalogContentDownloadDirectory()) ? "" : additionalDownloadStoragePathForCatalog;
        this.shouldStorePurchasedContentWithFriendlyName = true;
        this.maxNumConcurrentDownload = i > 2 ? 2 : 1;
        this.minimumFreeSpace = getMinFreeSpaceToLeave();
        this.maximumStorageSize = getMaxStorageSize();
        this.maximumCacheSize = getMaxCacheSize();
        this.downloadQualityPreference = getUserApplicableDownloadQualitySelected();
    }

    static {
        String simpleName = HarleyDownloadProperties.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "HarleyDownloadProperties::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public long getDownloadProgressNotificationInterval() {
        return this.downloadProgressNotificationInterval;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public String getPurchasedContentDownloadDirectory() {
        return this.purchasedContentDownloadDirectory;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public String getCatalogContentDownloadDirectory() {
        return this.catalogContentDownloadDirectory;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public String getAdditionalDownloadStoragePathForCatalog() {
        return this.additionalDownloadStoragePathForCatalog;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public boolean getShouldStorePurchasedContentWithFriendlyName() {
        return this.shouldStorePurchasedContentWithFriendlyName;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public int getMaxNumConcurrentDownload() {
        return this.maxNumConcurrentDownload;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public void setMaxNumConcurrentDownload(int i) {
        this.maxNumConcurrentDownload = i;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public long getMinimumFreeSpace() {
        return this.minimumFreeSpace;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public long getMaximumStorageSize() {
        return this.maximumStorageSize;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public long getMaximumCacheSize() {
        return this.maximumCacheSize;
    }

    @Override // com.amazon.mp3.playback.harley.DownloadProperties
    public DownloadQualityPreferences getDownloadQualityPreference() {
        return this.downloadQualityPreference;
    }

    private final String getPurchasedDownloadDirectory(StorageLocationFileManager storageLocationFileManager, boolean isSdCardAvailable) throws ExternalStorageNotSupportedException, FileNotFoundException {
        String catalogDownloadDirectory = INSTANCE.getCatalogDownloadDirectory(storageLocationFileManager, isSdCardAvailable);
        if (!(catalogDownloadDirectory.length() > 0)) {
            return catalogDownloadDirectory;
        }
        return catalogDownloadDirectory + ((Object) FileNameGenerator.DIR_SEPARATOR) + "Purchased";
    }

    private final DownloadQualityPreferences getUserApplicableDownloadQualitySelected() {
        DownloadQualityPreferences downloadQualityPreferencesFromEntryValue = DownloadQualityPreferences.INSTANCE.fromEntryValue(SettingsUtil.getDownloadQualityPreference(AmazonApplication.getContext()));
        if (downloadQualityPreferencesFromEntryValue == DownloadQualityPreferences.AUTO && !UserSubscriptionUtil.getUserSubscription().isKatana()) {
            downloadQualityPreferencesFromEntryValue = DownloadQualityPreferences.HIGH;
        }
        Log.info(TAG, Intrinsics.stringPlus("Setting the download quality preference selected by user ", downloadQualityPreferencesFromEntryValue.convertToQualitySetting()));
        return downloadQualityPreferencesFromEntryValue;
    }

    private final long getMinFreeSpaceToLeave() {
        try {
            long totalSpaceMB = (getTotalSpaceMB() * ((long) 10)) / ((long) 100);
            if (500 > totalSpaceMB) {
                Log.info(TAG, "Configuring to stop downloads when " + totalSpaceMB + " MBs remain");
            } else {
                Log.warning(TAG, "Configuring to stop downloads when 500 MBs remain");
                totalSpaceMB = 500;
            }
            return totalSpaceMB;
        } catch (FileNotFoundException e) {
            String str = TAG;
            Log.error(str, e.getMessage());
            Log.error(str, "Could not determine total space. Configuring to stop downloads when 0 MBs is remaining");
            return 0L;
        }
    }

    private final long getMaxCacheSize() throws ExternalStorageNotSupportedException {
        try {
            long totalSpaceMB = getTotalSpaceMB();
            Log.info(TAG, "Setting streaming cache size max up to " + totalSpaceMB + " MBs.");
            return totalSpaceMB;
        } catch (FileNotFoundException e) {
            String str = TAG;
            Log.error(str, e.getMessage());
            Log.error(str, "Could not determine total space. Setting streaming cache size max to 0 MB.");
            return 0L;
        }
    }

    private final long getMaxStorageSize() throws ExternalStorageNotSupportedException {
        try {
            long totalSpaceMB = getTotalSpaceMB();
            Log.info(TAG, "Setting download size max to " + totalSpaceMB + " MBs");
            return totalSpaceMB;
        } catch (FileNotFoundException e) {
            String str = TAG;
            Log.error(str, e.getMessage());
            Log.error(str, "Could not determine total space. Setting max download size max to 0 MB.");
            return 0L;
        }
    }

    private final long getTotalSpaceMB() throws ExternalStorageNotSupportedException, FileNotFoundException {
        StorageLocation preferredStorageLocation = getPreferredStorageLocation(this.isSdCardAvailable);
        File storageDirectory = this.storageLocationFileManager.getStorageDirectory(preferredStorageLocation, StorageLocationFileManager.Visibility.PUBLIC);
        Intrinsics.checkNotNullExpressionValue(storageDirectory, "storageLocationFileManag…anager.Visibility.PUBLIC)");
        Long totalSpaceMB = this.storageInfo.getTotalSpaceFromPathMB(storageDirectory);
        if (totalSpaceMB != null && totalSpaceMB.longValue() == 0 && StorageLocation.DEVICE == preferredStorageLocation) {
            Log.info(TAG, "Trying to determine available internal storage space using fallback mechanism.");
            totalSpaceMB = Long.valueOf(this.storageInfo.getTotalInternalStorageMB());
        }
        if (totalSpaceMB != null && totalSpaceMB.longValue() == 0) {
            boolean zAreAllGranted = GrantPermissionActivity.areAllGranted(AmazonApplication.getContext(), MusicDownloader.PERMISSIONS_FOR_DOWNLOAD);
            Log.error(TAG, "could not get total space for path: " + ((Object) storageDirectory.getPath()) + ", exists: " + storageDirectory.exists() + " and permission for storage is granted: " + zAreAllGranted);
        }
        if (totalSpaceMB != null && totalSpaceMB.longValue() == 0) {
            Log.error(TAG, "Could not determine total space. Returning 0 MB.");
        }
        Log.warning(TAG, "Total space in storage is " + totalSpaceMB + " MB.");
        Intrinsics.checkNotNullExpressionValue(totalSpaceMB, "totalSpaceMB");
        return totalSpaceMB.longValue();
    }

    private final StorageLocation getPreferredStorageLocation(boolean isSdCardAvailable) {
        if (!isSdCardAvailable) {
            return StorageLocation.DEVICE;
        }
        if (!isSdCardAvailable) {
            throw new NoWhenBranchMatchedException();
        }
        StorageLocation preferredStorageLocation = new StorageLocationPreference(AmazonApplication.getContext()).getPreferredStorageLocation();
        Intrinsics.checkNotNullExpressionValue(preferredStorageLocation, "{\n                Storag…ageLocation\n            }");
        return preferredStorageLocation;
    }
}
