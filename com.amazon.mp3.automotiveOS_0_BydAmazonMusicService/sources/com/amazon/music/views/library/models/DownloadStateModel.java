package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadStateModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/views/library/models/DownloadStateModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "downloadState", "Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "downloadProgress", "", "(Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;I)V", "getDownloadProgress", "()I", "getDownloadState", "()Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "equals", "", "other", "", "hashCode", "DownloadState", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DownloadStateModel extends BaseViewModel {
    private final int downloadProgress;
    private final DownloadState downloadState;

    /* JADX INFO: compiled from: DownloadStateModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/music/views/library/models/DownloadStateModel$DownloadState;", "", "(Ljava/lang/String;I)V", "NO_STATE", "DOWNLOADING", "DOWNLOADED", "ERROR_DOWNLOADING", "QUEUING", "AVAILABLE_FOR_DOWNLOAD", "ERROR_SYNC", "ERROR_DOWNLOAD_RETURN", "REDOWNLOAD", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum DownloadState {
        NO_STATE,
        DOWNLOADING,
        DOWNLOADED,
        ERROR_DOWNLOADING,
        QUEUING,
        AVAILABLE_FOR_DOWNLOAD,
        ERROR_SYNC,
        ERROR_DOWNLOAD_RETURN,
        REDOWNLOAD
    }

    public final DownloadState getDownloadState() {
        return this.downloadState;
    }

    public final int getDownloadProgress() {
        return this.downloadProgress;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadStateModel(DownloadState downloadState, int i) {
        super(null, 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(downloadState, "downloadState");
        this.downloadState = downloadState;
        this.downloadProgress = i;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (other instanceof DownloadStateModel) {
            DownloadStateModel downloadStateModel = (DownloadStateModel) other;
            DownloadState downloadState = downloadStateModel.downloadState;
            DownloadState downloadState2 = this.downloadState;
            if (downloadState == downloadState2) {
                if (!(downloadState2 == DownloadState.DOWNLOADING || downloadState2 == DownloadState.ERROR_DOWNLOADING) || downloadStateModel.downloadProgress == this.downloadProgress) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        return this.downloadState.hashCode();
    }
}
