package com.amazon.mp3.download;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.playback.harley.DownloadProperties;
import com.amazon.mp3.playback.harley.HarleyDownloadProperties;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadCoordinatorConfigUpdater.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004R\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorConfigUpdater;", "", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "harleyDownloadProperties", "", "shouldRetry", "refresh", "updatePreferredStorageLocation", "changeSDCardAvailability", "", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class DownloadCoordinatorConfigUpdater {
    public static final DownloadCoordinatorConfigUpdater INSTANCE = new DownloadCoordinatorConfigUpdater();
    private static final String TAG;

    private DownloadCoordinatorConfigUpdater() {
    }

    static {
        String simpleName = DownloadCoordinatorConfigUpdater.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "DownloadCoordinatorConfi…er::class.java.simpleName");
        TAG = simpleName;
    }

    public final boolean updatePreferredStorageLocation() {
        String str = TAG;
        Log.info(str, "Changing preferred storage location to reflect what's in shared preference.");
        boolean zRefresh$default = refresh$default(this, null, false, 3, null);
        Log.info(str, "Changing preferred storage location successful: " + zRefresh$default + '.');
        return zRefresh$default;
    }

    public final boolean changeSDCardAvailability() {
        String str = TAG;
        Log.info(str, "Changing SD card Availability.");
        boolean zRefresh$default = refresh$default(this, null, false, 3, null);
        Log.info(str, "Changing SD card Availability to successful: " + zRefresh$default + '.');
        return zRefresh$default;
    }

    static /* synthetic */ boolean refresh$default(DownloadCoordinatorConfigUpdater downloadCoordinatorConfigUpdater, DownloadProperties downloadProperties, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            downloadProperties = new HarleyDownloadProperties(null, 0, null, 7, null);
        }
        if ((i & 2) != 0) {
            z = true;
        }
        return downloadCoordinatorConfigUpdater.refresh(downloadProperties, z);
    }

    private final boolean refresh(final DownloadProperties harleyDownloadProperties, boolean shouldRetry) {
        if (!AccountCredentialUtil.get().isSignedIn() || !MusicDownloader.getInstance(AmazonApplication.getContext()).hasStoragePermissions()) {
            return false;
        }
        long jUpdateConfigOnCurrentThread = DownloadCoordinatorProvider.INSTANCE.getDownloadCoordinatorWrapper().updateConfigOnCurrentThread(harleyDownloadProperties);
        if (jUpdateConfigOnCurrentThread == 0) {
            return true;
        }
        if (jUpdateConfigOnCurrentThread != 2) {
            return false;
        }
        String str = TAG;
        Log.info(str, "Refreshing Download Coordinator Config failed because downloads are in progress.");
        if (!shouldRetry) {
            return false;
        }
        Log.info(str, "Will retry when ongoing download completes.");
        MusicDownloader.getInstance(AmazonApplication.getContext()).enqueueEndOfDownloadCollectionTask(new Runnable() { // from class: com.amazon.mp3.download.DownloadCoordinatorConfigUpdater$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                DownloadCoordinatorConfigUpdater.m918refresh$lambda2(harleyDownloadProperties);
            }
        });
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: refresh$lambda-2, reason: not valid java name */
    public static final void m918refresh$lambda2(DownloadProperties harleyDownloadProperties) {
        Intrinsics.checkNotNullParameter(harleyDownloadProperties, "$harleyDownloadProperties");
        INSTANCE.refresh(harleyDownloadProperties, true);
    }
}
