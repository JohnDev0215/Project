package com.amazon.mp3.download;

import com.amazon.digitalmusicplayback.StorageInitError;
import com.amazon.mp3.library.provider.source.local.DeleteListener;
import com.amazon.mp3.playback.harley.DownloadProperties;
import com.amazon.music.downloads.DMMDownloadCoordinator;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: DownloadCoordinatorWrapperInterface.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H&J\b\u0010\t\u001a\u00020\bH&J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH'J\u0018\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\bH&J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\bH&J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0011H&J\b\u0010\u0015\u001a\u00020\bH&J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0011H&J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0017H&¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/download/DownloadCoordinatorWrapperInterface;", "Lcom/amazon/music/downloads/DMMDownloadCoordinator;", "", "asin", "Lcom/amazon/mp3/library/provider/source/local/DeleteListener;", "deleteListener", "", "removeDownload", "", "removeAllDRMDownloads", "identifier", "isDrm", "isTrackAvailableOffline", "Lcom/amazon/mp3/download/HarleyDownloadedItem;", "getDownloadedItem", "value", "setDownloadOnWifiOnly", "Lcom/amazon/mp3/playback/harley/DownloadProperties;", "downloadProperties", "", "updateConfigOnCurrentThread", "isOfflineDRMLicenseAvailable", "getCurrentDownloadProperties", "Lcom/amazon/digitalmusicplayback/StorageInitError;", "getInitError", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public interface DownloadCoordinatorWrapperInterface extends DMMDownloadCoordinator {
    DownloadProperties getCurrentDownloadProperties();

    HarleyDownloadedItem getDownloadedItem(String identifier, boolean isDrm);

    StorageInitError getInitError();

    boolean isOfflineDRMLicenseAvailable();

    @Deprecated(message = "Don't use this", replaceWith = @ReplaceWith(expression = "getDownloadedItem(identifier, isDrm).isAvailableOffline()", imports = {}))
    boolean isTrackAvailableOffline(String identifier, boolean isDrm);

    boolean removeAllDRMDownloads();

    void removeDownload(String asin, DeleteListener deleteListener);

    void setDownloadOnWifiOnly(boolean value);

    long updateConfigOnCurrentThread(DownloadProperties downloadProperties);
}
