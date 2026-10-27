package com.amazon.mp3.download;

import com.amazon.digitalmusicplayback.PlayerAudioQuality;
import com.amazon.digitalmusicplayback.StorageInitError;
import com.amazon.digitalmusicplayback.TrackQualityEntry;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.playback.harley.DownloadProperties;
import com.amazon.mp3.util.Log;
import com.amazon.music.downloads.DMMDownloadCoordinator;
import com.amazon.music.downloads.DownloadItemInfo;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: NoOpDownloadCoordinatorWrapper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b$\u0010%J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0016J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016J\u001a\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004H\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u0004H\u0016J\u0010\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\n\u0010\u001f\u001a\u0004\u0018\u00010\u001bH\u0016J\u0018\u0010!\u001a\u00020 2\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0004H\u0016R\u0014\u0010\"\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006&"}, d2 = {"Lcom/amazon/mp3/download/NoOpDownloadCoordinatorWrapper;", "Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "Lcom/amazon/digitalmusicplayback/StorageInitError;", "getInitError", "", "removeAllDRMDownloads", "isOfflineDRMLicenseAvailable", "Lcom/amazon/music/downloads/DownloadItemInfo;", "downloadItemInfo", "", "download", "pause", "resume", "cancelDownload", "Lcom/amazon/music/downloads/DMMDownloadCoordinator$DMMDownloadListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerListener", "", "asin", "Lcom/amazon/mp3/library/provider/source/local/DeleteListener;", "deleteListener", "removeDownload", "identifier", "isDrm", "isTrackAvailableOffline", "value", "setDownloadOnWifiOnly", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "downloadProperties", "", "updateConfigOnCurrentThread", "getCurrentDownloadProperties", "Lcom/amazon/mp3/download/HarleyDownloadedItem;", "getDownloadedItem", "TAG", "Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class NoOpDownloadCoordinatorWrapper implements DownloadCoordinatorWrapperInterface {
    public static final NoOpDownloadCoordinatorWrapper INSTANCE = new NoOpDownloadCoordinatorWrapper();
    private static final String TAG;

    private NoOpDownloadCoordinatorWrapper() {
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public StorageInitError getInitError() {
        Log.info(TAG, "Called getInitErrors");
        return null;
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public boolean removeAllDRMDownloads() {
        Log.info(TAG, "Called removeAllDRMDownloads");
        return false;
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public boolean isOfflineDRMLicenseAvailable() {
        Log.info(TAG, "Called isOfflineDRMLicenseAvailable");
        return false;
    }

    static {
        String simpleName = NoOpDownloadCoordinatorWrapper.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "NoOpDownloadCoordinatorW…er::class.java.simpleName");
        TAG = simpleName;
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void download(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Download issued with ", downloadItemInfo));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void pause(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Pause issued with ", downloadItemInfo));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void resume(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Resume issued with ", downloadItemInfo));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void cancelDownload(DownloadItemInfo downloadItemInfo) {
        Intrinsics.checkNotNullParameter(downloadItemInfo, "downloadItemInfo");
        Log.info(TAG, Intrinsics.stringPlus("Cancel issued with ", downloadItemInfo));
    }

    @Override // com.amazon.music.downloads.DMMDownloadCoordinator
    public void registerListener(DMMDownloadCoordinator.DMMDownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        Log.info(TAG, "Registering listener");
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public void removeDownload(String asin, DeleteListener deleteListener) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        Log.info(TAG, Intrinsics.stringPlus("Remove download issued for ASIN ", asin));
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public boolean isTrackAvailableOffline(String identifier, boolean isDrm) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        if (isDrm) {
            return false;
        }
        File file = new File(identifier);
        return file.exists() && file.canRead();
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public void setDownloadOnWifiOnly(boolean value) {
        Log.info(TAG, Intrinsics.stringPlus("Set DownloadOnWifiOnly to ", Boolean.valueOf(value)));
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public long updateConfigOnCurrentThread(DownloadProperties downloadProperties) {
        Intrinsics.checkNotNullParameter(downloadProperties, "downloadProperties");
        Log.info(TAG, Intrinsics.stringPlus("Updating DownloadCoordinatorConfig to ", downloadProperties));
        return 1L;
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public DownloadProperties getCurrentDownloadProperties() {
        Log.info(TAG, "Current download properties: null.");
        return null;
    }

    @Override // com.amazon.mp3.download.DownloadCoordinatorWrapperInterface
    public HarleyDownloadedItem getDownloadedItem(String identifier, boolean isDrm) {
        Intrinsics.checkNotNullParameter(identifier, "identifier");
        Log.info(TAG, "Track downloaded quality: null.");
        return new HarleyDownloadedItem(new TrackQualityEntry(PlayerAudioQuality.UNKNOWN, ""), isDrm);
    }
}
