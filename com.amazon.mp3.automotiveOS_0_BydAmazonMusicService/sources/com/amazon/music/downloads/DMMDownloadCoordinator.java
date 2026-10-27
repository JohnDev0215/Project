package com.amazon.music.downloads;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* JADX INFO: compiled from: DMMDownloadCoordinator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\fJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\r"}, d2 = {"Lcom/amazon/music/downloads/DMMDownloadCoordinator;", "", "Lcom/amazon/music/downloads/DownloadItemInfo;", "downloadItemInfo", "", "download", "pause", "resume", "Lcom/amazon/music/downloads/DMMDownloadCoordinator$DMMDownloadListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "registerListener", "cancelDownload", "DMMDownloadListener", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0})
public interface DMMDownloadCoordinator {

    /* JADX INFO: compiled from: DMMDownloadCoordinator.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lcom/amazon/music/downloads/DMMDownloadCoordinator$DMMDownloadListener;", "", "downloadComplete", "", "ID", "", "error", "Lcom/amazon/music/downloads/ErrorReason;", "location", "downloadPaused", "downloadResumed", "progressUpdate", "percent", "", "DMMDownloadManager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface DMMDownloadListener {
        void downloadComplete(String ID, ErrorReason error, String location);

        void downloadPaused(String ID, ErrorReason error);

        void downloadResumed(String ID, ErrorReason error);

        void progressUpdate(String ID, float percent);
    }

    void cancelDownload(DownloadItemInfo downloadItemInfo);

    void download(DownloadItemInfo downloadItemInfo);

    void pause(DownloadItemInfo downloadItemInfo);

    void registerListener(DMMDownloadListener listener);

    void resume(DownloadItemInfo downloadItemInfo);
}
